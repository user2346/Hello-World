package test.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class TestActivity extends Activity {
	private String html = "";
	private Handler mHandler;
	ProgressDialog pd;
	
	private Thread checkUpdate = new Thread() {
		public void run() {
			try {
				URL updateURL = new URL("http://stream.school.nz");
				URLConnection conn = updateURL.openConnection();
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				ByteArrayBuffer baf = new ByteArrayBuffer(50);

				int current = 0;
				while((current = bis.read()) != -1){
					baf.append((byte)current);
				}
				
				/* Convert the Bytes read to a String. */
				html = new String(baf.toByteArray());
				mHandler.post(showUpdate);
			} catch (Exception e) {
				
			}
		}
	};

	private Runnable showUpdate = new Runnable(){
		public void run(){
			TestActivity.this.getCurrentEventsFromSource(html);
			TestActivity.this.pd.dismiss();
		}
	};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		byte[] buf = new byte[1024];
		
		try {
			FileInputStream fis = openFileInput("EventCache.txt");
			fis.read(buf);
			fis.close();
		} catch (Exception e) {
			logE(e.toString());
		}
		
		String cache = new String(buf);
		
		if (!cache.isEmpty() && !cache.equals(null) && !buf.equals(null)) {
			TextView tv = new TextView(this);
			tv.setText(cache);
			setContentView(tv);
		} else {
			pd = ProgressDialog.show(this, "Loading", "Fetching data", true, true);
			mHandler = new Handler();
			checkUpdate.start();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.refresh:
	        refresh();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	/** Refreshes the events by showing dialog and rerunning the thread */
	public void refresh() {
		pd = ProgressDialog.show(this, "Loading", "Fetching data", true, true);
		mHandler = new Handler();
		checkUpdate = new Thread() {
			public void run() {
				try {
					URL updateURL = new URL("http://stream.school.nz");
					URLConnection conn = updateURL.openConnection();
					InputStream is = conn.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					ByteArrayBuffer baf = new ByteArrayBuffer(50);

					int current = 0;
					while((current = bis.read()) != -1){
						baf.append((byte)current);
					}
					
					/* Convert the Bytes read to a String. */
					html = new String(baf.toByteArray());
					mHandler.post(showUpdate);
				} catch (Exception e) {
					
				}
			}
		};
		checkUpdate.start();
	}
	
	public void getCurrentEventsFromSource(String source) {
		int indexStart = source.indexOf("<h3>Current");
		int indexEnd = source.indexOf("Subscribe to RSS Feed</a></div>");
		String events = source.substring(indexStart,indexEnd+"Subscribe to RSS Feed</a></div>".length());
		StringBuffer sb = new StringBuffer();
		
		while (events.indexOf("=\">") != -1) {
			sb.append(getEventFromString(events) + " on " + getDateFromString(events) + ",\n");
			events = events.substring(events.indexOf("</a></span></td></tr>")+1);
		}
		
		TextView tv = new TextView(this);
		tv.setText(sb.toString());
		setContentView(tv);

		try {
			FileOutputStream fos = openFileOutput("EventCache.txt", Context.MODE_PRIVATE);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			logE(e.toString());
		}
	}

	/** Gets an event from some random html that St Pats uses... */
	public String getEventFromString(String events) {
		int indexStart = events.indexOf("=\">");
		int indexEnd = events.indexOf("</a></span></td></tr>");
		
		try {
			return events.substring(indexStart+3,indexEnd);
		} catch (Exception e) {
			logE("Events : " + events);
			logE("Start : " + indexStart);
			logE("End : " + indexEnd);
			return "";
		}
	}

	/** Gets a date from some random html that St Pats uses... */
	public String getDateFromString(String dates) {
		int indexStart = dates.indexOf("date\">");
		int indexEndOne = dates.indexOf("</span><br");
		int indexEndTwo = dates.indexOf("</span> -");
		int indexEnd = Math.min(indexEndOne, indexEndTwo);
		if (indexEndTwo == -1) {
			indexEnd = indexEndOne;
		}
		if (indexEndOne == -1) {
			indexEnd = indexEndTwo;
		}
		
		try {
			return dates.substring(indexStart+6,indexEnd);
		} catch (Exception e) {
			logE("Dates : " + dates);
			logE("Start : " + indexStart);
			logE("One : " + indexEndOne);
			logE("Two : " + indexEndTwo);
			logE("End : " + indexEnd);
			return "";
		}
	}

	public void log(String message) {
		Log.d("aLog", message);
	}
	
	public void logE(String message) {
		Log.e("aLog", message);
	}
}

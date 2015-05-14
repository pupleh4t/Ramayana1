package android.syalala.ramayana;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

	final int[] jumlahBaju = {R.id.jumBaju1, R.id.jumBaju2, R.id.jumBaju3, R.id.jumBaju4, R.id.jumBaju5, R.id.jumBaju6, R.id.jumBaju7, R.id.jumBaju8, R.id.jumBaju9, R.id.jumBaju10, R.id.jumBaju11, R.id.jumBaju12, R.id.jumBaju13};
	final int[] hargaBaju = {R.id.harBaju1, R.id.harBaju2, R.id.harBaju3, R.id.harBaju4, R.id.harBaju5, R.id.harBaju6, R.id.harBaju7, R.id.harBaju8, R.id.harBaju9, R.id.harBaju10, R.id.harBaju11, R.id.harBaju12, R.id.harBaju13};
	final int[] jumlahCelana = {R.id.jumCelana1, R.id.jumCelana2, R.id.jumCelana3, R.id.jumCelana4, R.id.jumCelana5, R.id.jumCelana6};
	final int[] hargaCelana = {R.id.harCelana1, R.id.harCelana2, R.id.harCelana3, R.id.harCelana4, R.id.harCelana5, R.id.harCelana6};
	final int[] jumlahSendal = {R.id.jumSendal1, R.id.jumSendal2, R.id.jumSendal3, R.id.jumSendal4, R.id.jumSendal5, R.id.jumSendal6, R.id.jumSendal7, R.id.jumSendal8, R.id.jumSendal9, R.id.jumSendal10, R.id.jumSendal11, R.id.jumSendal12, R.id.jumSendal13, R.id.jumSendal14, R.id.jumSendal15};
	final int[] hargaSendal = {R.id.harSendal1, R.id.harSendal2, R.id.harSendal3, R.id.harSendal4, R.id.harSendal5, R.id.harSendal6, R.id.harSendal7, R.id.harSendal8, R.id.harSendal9, R.id.harSendal10, R.id.harSendal11, R.id.harSendal12, R.id.harSendal13, R.id.harSendal14,R.id.harSendal15,};
	double jumlahPesanBaju=0;
	double jumlahPesanCelana=0;
	double jumlahPesanSendal=0;
	Button btnClose25;
	public PopupWindow pwindo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.menuJualan);
		myPager.setOffscreenPageLimit(4);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);
	}

	public void totalHarga(View v){
		try {
			hargaTotalBaju();
			hargaTotalCelana();
			hargaTotalSendal();
			double jumlahTotal = (double)jumlahPesanBaju+(double)jumlahPesanCelana+(double)jumlahPesanSendal;
			TextView txtTotal = (TextView)findViewById(R.id.txtHarga);
			if (jumlahTotal<=1000000){
				txtTotal.setText("Total Harga : "+String.valueOf(jumlahTotal));
			} else if (jumlahTotal>1000000){
				jumlahTotal = jumlahTotal * 0.9;
				txtTotal.setText("Total Harga : "+String.valueOf(jumlahTotal)+" anda dapat diskon 10%");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void lihatDetail (View v){
		initiatePopupWindowBaju();
	}

	private void initiatePopupWindowBaju() {
		try {
			LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.menu_popup_baju,(ViewGroup)findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, 500, 500, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lihatDetailCelana (View v){
		initiatePopupWindowCelana();
	}

	private void initiatePopupWindowCelana() {
		try {
			LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.menu_popup_celana,(ViewGroup)findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, 500, 500, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lihatDetailSendal (View v){
		initiatePopupWindowSendal();
	}

	private void initiatePopupWindowSendal() {
		try {
			LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.menu_popup_sendal,(ViewGroup)findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, 500, 500, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close25 (View v){
		pwindo.dismiss();
	}

	public double hargaTotalBaju(){
		double num_pesan = 0;
		double num_harga = 0;
		jumlahPesanBaju = 0;
		for (int a=0; a<jumlahBaju.length;a++){

			EditText getJumlah = (EditText)findViewById(jumlahBaju[a]);
			TextView getHarga = (TextView)findViewById(hargaBaju[a]);

			String hasil = getJumlah.getText().toString();
			String harga = getHarga.getText().toString();
			if (hasil != ""){
				num_pesan = Integer.valueOf(hasil);
				num_harga = Integer.valueOf(harga);
				jumlahPesanBaju = jumlahPesanBaju+(num_harga * num_pesan * 0.75);
			} else if (hasil == ""){
				num_pesan = 0;
				jumlahPesanBaju = jumlahPesanBaju+(num_harga * num_pesan * 0.75);
			}
		}
		return jumlahPesanBaju;
	}

	public double hargaTotalSendal(){
		int num_pesan = 0;
		int num_harga = 0;
		jumlahPesanSendal = 0;
		for (int a=0; a<jumlahSendal.length;a++){

			EditText getJumlah = (EditText)findViewById(jumlahSendal[a]);
			TextView getHarga = (TextView)findViewById(hargaSendal[a]);

			String hasil = getJumlah.getText().toString();
			String harga = getHarga.getText().toString();
			if (hasil != ""){
				num_pesan = Integer.valueOf(hasil);
				num_harga = Integer.valueOf(harga);

				jumlahPesanSendal = jumlahPesanSendal+(num_harga * num_pesan * 0.6);
			} else if (hasil == ""){
				num_pesan = 0;
				jumlahPesanSendal = jumlahPesanSendal+(num_harga * num_pesan * 0.6);
			}
		}
		return jumlahPesanSendal;
	}

	public double hargaTotalCelana(){
		int num_pesan = 0;
		int num_harga = 0;
		jumlahPesanCelana = 0;
		for (int a=0; a<jumlahCelana.length;a++){

			EditText getJumlah = (EditText)findViewById(jumlahCelana[a]);
			TextView getHarga = (TextView)findViewById(hargaCelana[a]);

			String hasil = getJumlah.getText().toString();
			String harga = getHarga.getText().toString();
			if (hasil != ""){
				num_pesan = Integer.valueOf(hasil);
				num_harga = Integer.valueOf(harga);
				jumlahPesanCelana = jumlahPesanCelana+(num_harga * num_pesan * 0.5);
			} else if (hasil == ""){
				num_pesan = 0;
				jumlahPesanCelana = jumlahPesanCelana+(num_harga * num_pesan *0.5);
			}
		}
		return jumlahPesanCelana;
	}

	private class MyPagerAdapter extends PagerAdapter {

		public int getCount() {
			return 4;
		}

		public Object instantiateItem(View collection, int position) {

			LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			int resId = 0;
			switch (position) {
				case 0:
					resId = R.layout.menu_total;
					break;
				case 1:
					resId = R.layout.menu_baju;
					break;
				case 2:
					resId = R.layout.menu_celana;
					break;
				case 3:
					resId = R.layout.menu_sendal;
					break;
			}

			View view = inflater.inflate(resId, null);

			((ViewPager) collection).addView(view, 0);

			return view;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);

		}

		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);

		}

		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

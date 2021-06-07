// TODO:コメントをもっと丁寧に追記していく
package myBoardWithoutFrameworkFileBacked;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MyBoardWithoutFrameworkFileBackedDAO {

	private String dataFile = new String();

	//コンストラクタ
	// - データファイルの位置を引数で引き継いで、データファイルの場所を特定する
	MyBoardWithoutFrameworkFileBackedDAO(String _path) {
		this.dataFile = _path + "/posts.dat";
	}

	// 登録されている記事を全件取得し、呼び出し元にBeasを返却する
	public List<MyBoardWithoutFrameworkFileBacked> selectAllPosts(){

		 List<MyBoardWithoutFrameworkFileBacked> results = loadAllPosts(new File(this.dataFile));
		 return results;


	}

	// 指定の投稿番号をロードした投稿データから削除しデータファイルに保存する
	public void deletePost(int _id) {
		File df = new File(this.dataFile);
		List<MyBoardWithoutFrameworkFileBacked> loadedPosts = loadAllPosts(df);
		for (int i=0; i < loadedPosts.size(); i++) {
			if (loadedPosts.get(i).getId() == _id) {
				loadedPosts.remove(i);
			}
		}
		saveDataFile( df , loadedPosts);
	}

	// 指定の投稿番号をロードした投稿データから取得する
	public MyBoardWithoutFrameworkFileBacked selectPost(int id) {
		File df = new File(this.dataFile);
		List<MyBoardWithoutFrameworkFileBacked> loadedPosts = new ArrayList<MyBoardWithoutFrameworkFileBacked>();
		loadedPosts = loadAllPosts(df);
		int i = 0 ;
		while(loadedPosts.get(i).getId() != id ) {
			i++;
		}
		return loadedPosts.get(i);

	}
	// 投稿番号を指定し、投稿データを更新する
	public void updatePost(MyBoardWithoutFrameworkFileBacked _post) {
		Timestamp now =getCurrentTimestamp("yyyy/MM/dd hh:mm:ss.SSS");
		File df = new File(this.dataFile);
		List<MyBoardWithoutFrameworkFileBacked> loadedPosts = loadAllPosts(df);
		int i = 0 ;
		while(loadedPosts.get(i).getId() != _post.getId() ) {
			i++;
		}
		_post.setPostedAt(loadedPosts.get(i).getPostedAt());
		_post.setUpdatedAt(now);
		loadedPosts.set(i, _post);
		saveDataFile( df, loadedPosts );
	}
	// 新規の投稿をデータファイルに追記する
	public void insertPost(MyBoardWithoutFrameworkFileBacked _post) {
		Timestamp now =getCurrentTimestamp("yyyy/MM/dd hh:mm:ss.SSS");
		File df = new File(this.dataFile);
		List<MyBoardWithoutFrameworkFileBacked> loadedPosts = new ArrayList<MyBoardWithoutFrameworkFileBacked>();
		loadedPosts = loadAllPosts(df);
		_post.setId(getLatestId(loadedPosts));
		_post.setUpdatedAt(now);
		_post.setPostedAt(now);
		loadedPosts.add(_post);
			saveDataFile( df, loadedPosts );
	}
	// データファイルから全件ロードし、Beansのリストとして返却する
	private List<MyBoardWithoutFrameworkFileBacked> loadAllPosts(File _file) {
		List<MyBoardWithoutFrameworkFileBacked> results = new ArrayList<MyBoardWithoutFrameworkFileBacked>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(_file));
			String str;
			//MyBoardWithoutFrameworkFileBacked mb = new MyBoardWithoutFrameworkFileBacked();
			while((str = br.readLine()) != null) {
				MyBoardWithoutFrameworkFileBacked mb = new MyBoardWithoutFrameworkFileBacked();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				String[] splited = str.split("<>");
				mb.setId(Integer.parseInt(splited[0]));
				mb.setName(splited[1]);
				mb.setEmail(splited[2]);
				mb.setMsg(unEscapeCrLr(splited[3]));
				try {
					mb.setPostedAt(new Timestamp(sdf.parse(splited[4]).getTime()));
					mb.setUpdatedAt(new Timestamp(sdf.parse(splited[5]).getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				} finally {}
				results.add(mb);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {}
		return results;
	}

	// 投稿データのBeasリストをテキストファイルとして保存する
	private void saveDataFile(File _file, List<MyBoardWithoutFrameworkFileBacked> _posts) {
			try {
			BufferedWriter writer =new BufferedWriter(new FileWriter( _file ));
			for (int i = 0; i < _posts.size(); i++) {
				writer.write( parsePostToLine(_posts.get(i)));
				writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {}
	}
	// 投稿データのBeansを1行にパースする
	private String parsePostToLine(MyBoardWithoutFrameworkFileBacked mb) {
		return mb.getId() + "<>" + mb.getName() + "<>"
			      + mb.getEmail() + "<>" + escapeCrLr( mb.getMsg() ) + "<>"
			      + mb.getPostedAt() + "<>" + mb.getUpdatedAt();
	}

	private String unEscapeCrLr( String _str) {

		String regex="\\\\r\\\\n|\\\\r|\\\\n";
		return _str.replaceAll(regex, "\r\n");
	}

	private String escapeCrLr( String _str) {

		String regex="\r\n|\r|\n";
		return _str.replaceAll(regex, "\\\\r\\\\n");
	}

	// 引数のフォーマット文字列で現在時刻をパースしたTimetampを返す
	private Timestamp getCurrentTimestamp(String _fmt) {
		String parsedCurrentTime = new SimpleDateFormat(_fmt).format(new Timestamp(System.currentTimeMillis()));
		return new Timestamp(strToDate(parsedCurrentTime, _fmt).getTime());

	}
	// 引数のフォーマット文字列、文字列型の時刻データをDateオブジェクトを返す
	private Date strToDate(String _str, String _fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(_fmt);
		sdf.setLenient(false);
		try {
			return sdf.parse(_str);
		} catch (ParseException e) {
			return null;
		} finally {
		sdf = null;
		}
	}
	// 投稿番号を採番する
	private int getLatestId(List<MyBoardWithoutFrameworkFileBacked> _posts){
		int finalId = 0;

		//最終の投稿番号を検索
		for (int i=0; i < _posts.size(); i++){
			int _id = _posts.get(i).getId();
			if( finalId < _id ) {
				finalId=_id;
			}
		}
		//検索した最終番号に1加算した値を返却
		return finalId + 1;
	}
}




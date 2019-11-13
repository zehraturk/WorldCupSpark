package com.udemy.spark.app;

import com.google.common.collect.Iterators;
import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import scala.Tuple2;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\winutils");

        JavaSparkContext cont=new JavaSparkContext("local","WorldCupApp");
        JavaRDD<String> Raw_Data = cont.textFile("C:\\Users\\Zehra\\Desktop\\Data\\WorldCupPlayers.csv");
        JavaRDD<PlayerModel> playersRDD = Raw_Data.map(new Function<String, PlayerModel>() {
            public PlayerModel call(String line) throws Exception {
                //limit -1 -->arrayindexofbound hatası için çözüm
                String[] player_Array = line.split(",", -1);
                return new PlayerModel(player_Array[0], player_Array[1], player_Array[2],
                        player_Array[3], player_Array[4], player_Array[5],
                        player_Array[6], player_Array[7], player_Array[8]);

            }
        });
        //takımı Türkiye olanlar
        JavaRDD<PlayerModel> TUR = playersRDD.filter(new Function<PlayerModel, Boolean>() {
            public Boolean call(PlayerModel playerModel) throws Exception {
                return playerModel.getTeam().equals("TUR");
            }
        });
        //messi kac mac yapti?
        /*JavaRDD<PlayerModel> messiRDD = playersRDD.filter(new Function<PlayerModel, Boolean>() {
            public Boolean call(PlayerModel playerModel) throws Exception {
                return playerModel.getPlayerName().equals("MESSI");
            }
        });
        System.out.println(messiRDD.count());*/

        //playerName,count(matchID) burada playerName:KEY count(matchID):VALUE olacak.
        final JavaPairRDD<String, String> map_playerRDD = TUR.mapToPair(new PairFunction<PlayerModel, String, String>() {
            public Tuple2<String, String> call(PlayerModel playerModel) throws Exception {
                return new Tuple2<String, String>(playerModel.getPlayerName(), playerModel.getMatchID());
            }
        });

        //KEY e göre grupladık.
        JavaPairRDD<String, Iterable<String>> group_playerRDD = map_playerRDD.groupByKey();

        JavaRDD<groupPlayer> resultRDD = group_playerRDD.map(new Function<Tuple2<String, Iterable<String>>, groupPlayer>() {
            public groupPlayer call(Tuple2<String, Iterable<String>> array) throws Exception {
                Iterator<String> iterator_raw = array._2.iterator();
                int size = Iterators.size(iterator_raw);
                return new groupPlayer(array._1, size);
            }
        });



        resultRDD.foreach(new VoidFunction<groupPlayer>() {
            public void call(groupPlayer groupPlayer) throws Exception {
                System.out.println(groupPlayer.getPlayerName()+" "+groupPlayer.getMatchCount());

            }
        });


    }
}

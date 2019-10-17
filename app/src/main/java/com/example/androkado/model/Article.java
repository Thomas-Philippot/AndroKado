package com.example.androkado.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity
public class Article implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo()
    private String nom;

    @ColumnInfo()
    private String description;

    @ColumnInfo()
    private double prix;

    @ColumnInfo()
    private double degreEnvie;

    @ColumnInfo()
    private String url;

    @ColumnInfo()
    private boolean isAchete;

    public Article(String nom, String description, double prix, double degreEnvie, String url, boolean isAchete) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.degreEnvie = degreEnvie;
        this.url = url;
        this.isAchete = isAchete;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Article(Parcel in) {
    }

    public int getUid() {
        return uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getDegreEnvie() {
        return degreEnvie;
    }

    public void setDegreEnvie(double degreEnvie) {
        this.degreEnvie = degreEnvie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAchete() {
        return isAchete;
    }

    public void setAchete(boolean achete) {
        isAchete = achete;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    /**
     * ==================
     * Comparators to sort the articles by properties
     * ==================
     */
    public static Comparator<Article> priceComparator = new Comparator<Article>() {

        public int compare(Article a1, Article a2) {
            int price1 = (int)a1.getPrix();
            int price2 = (int)a2.getPrix();

            return price1 - price2;
        }
    };

    public static Comparator<Article> nameComparator = new Comparator<Article>() {

        public int compare(Article a1, Article a2) {
            String name1 = a1.getNom();
            String name2 = a2.getNom();

            return name1.compareTo(name2);
        }
    };

    @Override
    public String toString() {
        return "Article{" +
                "uid=" + uid +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", degreEnvie=" + degreEnvie +
                ", url='" + url + '\'' +
                ", isAchete=" + isAchete +
                '}';
    }
}

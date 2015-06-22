package com.mobility.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple POJO representing a Board
 *
 * @author Shrikant Desai
 */
@Document (collection = "board")
public class Board {

	@Id 
	private int id;
	private String title;
    private String blurb;
    private String author;
    private String thumbnail_url;
    private String details_url;

    public Board(int id, String title, String blurb, String author, String thumbnail_url, String details_url  ) {
        this.id=id;
    	this.title = title;
        this.blurb = blurb;
        this.author=author;
        this.thumbnail_url = thumbnail_url;
        this.details_url = details_url;
    }

    public String getTitle() {
		return title;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getBlurb() {
		return blurb;
	}



	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getThumbnail_url() {
		return thumbnail_url;
	}



	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}



	public String getDetails_url() {
		return details_url;
	}



	public void setDetails_url(String details_url) {
		this.details_url = details_url;
	}




    public String toString() {

		return title+id;
    }

}
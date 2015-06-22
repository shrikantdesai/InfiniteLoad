package com.mobility.rest;

import java.util.List;

import com.mobility.model.Board;
public class multipleBoardResponse {
	private int page;
	private boolean morePages;
	public boolean isMorePages() {
		return morePages;
	}

	public void setMorePages(boolean morePages) {
		this.morePages = morePages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private boolean success;
	private List<Board> Boards;
	
	public multipleBoardResponse(int page,boolean morePages,boolean success, List<Board> Boards) {
		this.success = success;
		this.morePages= morePages;
		this.Boards = Boards;
		this.page=page;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Board> getBoards() {
		return Boards;
	}

	public void setBoards(List<Board> boards) {
		Boards = boards;
	}

}

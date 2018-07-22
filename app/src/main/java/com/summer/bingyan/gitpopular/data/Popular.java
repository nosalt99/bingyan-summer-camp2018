package com.summer.bingyan.gitpopular.data;

public class Popular {
    private String full_name;
    private long id;
    private owner owner;
    private String html_url;
    private String description;
    private boolean isFavorite=false;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public com.summer.bingyan.gitpopular.data.owner getOwner() {
        return owner;
    }
    public void setOwner(com.summer.bingyan.gitpopular.data.owner owner) {
        this.owner = owner;
    }
    public String getHtml_url() {
        return html_url;
    }
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}

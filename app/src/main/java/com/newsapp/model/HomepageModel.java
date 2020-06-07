

package com.newsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HomepageModel {

    @SerializedName("Banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("Ads")
    @Expose
    private List<Ad> ads = null;
    @SerializedName("News")
    @Expose
    private List<News> news = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public HomepageModel() {
    }




    @SerializedName("CategoryBotton")
    @Expose
    private List<CategoryBotton> categoryBotton = null;

    public List<CategoryBotton> getCategoryBotton() {
        return categoryBotton;
    }

    public void setCategoryBotton(List<CategoryBotton> categoryBotton) {
        this.categoryBotton = categoryBotton;
    }


    /**
     *
     * @param news
     * @param ads
     * @param banners
     */
    public HomepageModel(List<Banner> banners, List<Ad> ads, List<News> news) {
        super();
        this.banners = banners;
        this.ads = ads;
        this.news = news;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    public class Ad {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("image")
        @Expose
        private String image;

        /**
         * No args constructor for use in serialization
         *
         */
        public Ad() {
        }

        /**
         *
         * @param image
         * @param link
         * @param description
         * @param pid
         * @param title
         */
        public Ad(Integer pid, String title, String description, String link, String image) {
            super();
            this.pid = pid;
            this.title = title;
            this.description = description;
            this.link = link;
            this.image = image;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class Banner {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("image")
        @Expose
        private String image;

        /**
         * No args constructor for use in serialization
         *
         */
        public Banner() {
        }

        /**
         *
         * @param image
         * @param link
         * @param description
         * @param pid
         * @param title
         */
        public Banner(Integer pid, String title, String description, String link, String image) {
            super();
            this.pid = pid;
            this.title = title;
            this.description = description;
            this.link = link;
            this.image = image;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
    public class News {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("post_date")
        @Expose
        private String postDate;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("post_content")
        @Expose
        private String postContent;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("sourceUrl")
        @Expose
        private String sourceUrl;
        @SerializedName("source_logo")
        @Expose
        private String sourceLogo;
        @SerializedName("image")
        @Expose
        private String image;



        /**
         * No args constructor for use in serialization
         *
         */
        public News() {
        }

        /**
         *
         * @param sourceUrl
         * @param image
         * @param postContent
         * @param postDate
         * @param pid
         * @param source
         * @param sourceLogo
         * @param title
         * @param url
         */
        public News(Integer pid, String postDate, String title, String url, String postContent, String source, String sourceUrl, String sourceLogo, String image) {
            super();
            this.pid = pid;
            this.postDate = postDate;
            this.title = title;
            this.url = url;
            this.postContent = postContent;
            this.source = source;
            this.sourceUrl = sourceUrl;
            this.sourceLogo = sourceLogo;
            this.image = image;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPostContent() {
            return postContent;
        }

        public void setPostContent(String postContent) {
            this.postContent = postContent;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getSourceLogo() {
            return sourceLogo;
        }

        public void setSourceLogo(String sourceLogo) {
            this.sourceLogo = sourceLogo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
    public class CategoryBotton {

        @SerializedName("cid")
        @Expose
        private Integer cid;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("taxonomy")
        @Expose
        private String taxonomy;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getCid() {
            return cid;
        }

        public void setCid(Integer cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTaxonomy() {
            return taxonomy;
        }

        public void setTaxonomy(String taxonomy) {
            this.taxonomy = taxonomy;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}



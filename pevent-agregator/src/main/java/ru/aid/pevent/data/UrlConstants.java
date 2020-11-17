package ru.aid.pevent.data;

public final class UrlConstants {

    public final static String BAR_URI = "https://kudago.com/public-api/v1.4/places/?location=msk&categories=bar&fields=id,title,description,site_url,is_closed,subway&text_format=plain&page_size=100";
    public final static String QUEST_URI_EVENT = "https://kudago.com/public-api/v1.4/events/?location=msk&fields=id,title,description,site_url,is_closed,subway&categories=quest&text_format=plain&page_size=100";
    public final static String QUEST_URI_PLACE = "https://kudago.com/public-api/v1.4/places/?categories=questroom&location=msk&fields=id,title,description,site_url,is_closed,subway&text_format=plain&page_size=100";
    public final static String STUDY_URI = "https://kudago.com/public-api/v1.4/events/?location=msk&fields=id,title,description,site_url,is_closed,subway&categories=education,exhibition&text_format=plain&page_size=100";

    private UrlConstants() {
    }
}

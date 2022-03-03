package com.example.java8;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // Optional.of 를 사용할 수 있지만 해당 API의 파라미터로 null이 들어가게 되면 동일하게 NullPointException발생
    // 잠재적으로 null일 확률이 있는 값을 Optional로 변경할 때는 Optional.ofNullable()을 이용
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }
}

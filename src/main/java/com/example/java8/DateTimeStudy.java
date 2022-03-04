package com.example.java8;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateTimeStudy {

    public static void main(String[] args) {
        Instant instant = Instant.now();

        // return DateTimeFormatter.ISO_INSTANT.format(this); 출력할 때 사용자 친화적으로 출력 toString()
        // 해당 Instant를 출력하면 UTC기준으로 출력해주기 때문에 현재 개발자의 local과 다를 수 있음.
        System.out.println("instant = " + instant);
        System.out.println("instant.toEpochMilli() = " + instant.toEpochMilli());

        // instant -> zonedDateTime
        ZonedDateTime toZonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println("toZonedDateTime = " + toZonedDateTime);

        // 사용자의 현재 system Zone을 확인할 수 있음.
        System.out.println("ZoneId.systemDefault() = " + ZoneId.systemDefault());

        // zone을 부여하여 DateTime을 얻어올 수 있음.
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.println("zonedDateTime = " + zonedDateTime); // output : 2022-03-03T23:16:04.934925+09:00[Asia/Seoul]

        // LocalDate, LocalTime, LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime); // Local이라고 명시되어 있는 것 처럼 현재 사용자의 system zone을 읽어서 TiemStamp를 뱉어준다.

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        // 기간 비교

        // Period
        LocalDate toDay = LocalDate.now();
        System.out.println("toDay = " + toDay);

        LocalDate myBirthDay = LocalDate.of(2022, Month.NOVEMBER, 27);
        System.out.println("myBirthDay = " + myBirthDay);

        // Period는 기간을 연, 월, 일로 표현하기 때문에 30일이 넘어가면 월에 정보가 담기게 된다.
        // 현 시간 기준으로 3월3일에서 11월27일까지 남은 일을 Period로 구하면 24일을 return받게 된다.
        Period until = Period.between(toDay, myBirthDay);

        int days = until.getDays();
        System.out.println("days = " + days); //output: 24

        // 그렇게 때문에 target까지 몇 일이 남았는지 확인하려면 `ChronoUnit` 라이브러리를 이용하여 구한다.
        long untilDay = ChronoUnit.DAYS.between(toDay, myBirthDay);
        System.out.println("untilDay = " + untilDay);

        // Duration
        Instant nowInstant = Instant.now();
        Instant nowPlus10 = Instant.now().plus(10, ChronoUnit.SECONDS);

        Duration between = Duration.between(nowInstant, nowPlus10);
        long duration = between.get(ChronoUnit.SECONDS);
        System.out.println("duration = " + duration);
    }
}
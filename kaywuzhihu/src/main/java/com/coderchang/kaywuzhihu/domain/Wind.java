package com.coderchang.kaywuzhihu.domain;

/**
 * Created by coderchang on 2016/6/5.
 */
public class Wind {
        private String dir;//风向
        private String speed;//风速
        private String sc;//风力等级


        public Wind(String dir, String speed, String sc) {
            this.dir = dir;
            this.speed = speed;
            this.sc = sc;
        }

        public Wind() {
        }


        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

    @Override
    public String toString() {
        return "Wind{" +
                "dir='" + dir + '\'' +
                ", speed='" + speed + '\'' +
                ", sc='" + sc + '\'' +
                '}';
    }
}

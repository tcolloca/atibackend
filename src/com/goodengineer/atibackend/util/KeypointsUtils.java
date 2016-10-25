package com.goodengineer.atibackend.util;

import com.goodengineer.atibackend.model.Band;

import java.util.List;

public class KeypointsUtils {

  public static void paintPoints(Band band, int[] rgb, List<Point> points) {
    int color;
    switch (band.getName()) {
      case "R":
        color = rgb[0];
        break;
      case "G":
        color = rgb[1];
        break;
      case "B":
        color = rgb[2];
        break;
      default:
        throw new IllegalStateException();
    }
    for (Point point: points) {
      if (point.x - 1 >= 0) {
        band.setPixel(point.x - 1, point.y, color);
      }
      if (point.y - 1 >= 0) {
        band.setPixel(point.x, point.y - 1, color);
      }
      if (point.x + 1 < band.getWidth()) {
        band.setPixel(point.x + 1, point.y, color);
      }
      if (point.y + 1 < band.getHeight()) {
        band.setPixel(point.x, point.y + 1, color);
      }
    }
  }
}

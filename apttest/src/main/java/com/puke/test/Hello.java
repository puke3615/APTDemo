// this is a test file
package com.puke.test;

import java.io.Serializable;
import java.lang.String;

public final class Hello implements Serializable {
  private String name;

  public Hello(String name) {
    this.name = name;}

  public String getName() {
    return this.name;}
}

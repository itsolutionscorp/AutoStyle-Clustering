#!/usr/bin/env ruby

# Exercism 26
# Space Age Calculator

class SpaceAge

  def initialize(age)
    @age = age
  end

  def seconds
    @age
  end

  def on_earth
    (@age / 31557600.0).round_to(2)
  end

  def on_mercury
    (on_earth / 0.2408467).round_to(2)
  end

  def on_venus
    (on_earth / 0.61519726).floor_to(2)
  end

  def on_mars
    (on_earth / 1.8808158).round_to(2)
  end

  def on_jupiter
    (on_earth / 11.862615).round_to(2)
  end

  def on_saturn
    (on_earth / 29.447498).round_to(2)
  end

  def on_uranus
    (on_earth / 84.016846).round_to(2)
  end

  def on_neptune
    (on_earth / 164.79132).round_to(2)
  end

end


# Special methods for adding new functions to the Ruby Float Class
module Rounding

  def round_to(x)
    (self * 10**x).round.to_f / 10**x
  end

  def floor_to(x)
    (self * 10**x).floor.to_f / 10**x
  end
end

class Float
  include Rounding
end

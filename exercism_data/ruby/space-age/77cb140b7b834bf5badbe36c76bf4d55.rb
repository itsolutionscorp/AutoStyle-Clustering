class SpaceAge
  attr_accessor :seconds

  @@planetary_time = {
    'earth' => 1.0,
    'mercury' => 0.2408467,
    'venus' =>  0.61519726,
    'mars' =>  1.8808158,
    'jupiter' =>  11.862615,
    'saturn' =>  29.447498,
    'uranus' =>  84.016846,
    'neptune' =>  164.79132
  }

  def planetary_time
    @@planetary_time
  end

  def method_missing(meth, *args, &block)
    if meth.to_s =~ /^on_(.+)$/
      calc_planetary_age($1)
    else
      super
    end
  end

  def initialize(seconds)
    @seconds = seconds
  end

  def earth_seconds
    seconds / 31557600.0
  end

  def calc_planetary_age(planet)
    (earth_seconds / planetary_time.fetch(planet)).round(2)
  end
end

class SpaceAge
  LUNAR_SECONDS = {
    earth:   31_557_600 * 1.0,
    mercury: 31_557_600 * 0.2408467,
    venus:   31_557_600 * 0.61519726,
    mars:    31_557_600 * 1.8808158,
    jupiter: 31_557_600 * 11.862615,
    saturn:  31_557_600 * 29.447498,
    uranus:  31_557_600 * 84.016846,
    neptune: 31_557_600 * 164.79132
  }

  attr_accessor :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def on_planet(planet)
    (@seconds / LUNAR_SECONDS[planet.to_sym]).round(2)
  end

  def method_missing(method, *args, &block)
    if method.to_s =~ /^on_(.+)$/
      on_planet($1)
    else
      super
    end
  end
end

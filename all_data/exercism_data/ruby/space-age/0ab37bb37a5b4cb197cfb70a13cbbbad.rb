# Solution for Exercism.io Ruby Space Age problem.
class SpaceAge
  attr_reader :seconds

  EARTH_YEAR_SECS = 31_557_600
  YEAR_LENGTH = {
    'earth'   => EARTH_YEAR_SECS * 1.0,
    'mercury' => EARTH_YEAR_SECS * 0.2408467,
    'venus'   => EARTH_YEAR_SECS * 0.61519726,
    'mars'    => EARTH_YEAR_SECS * 1.8808158,
    'jupiter' => EARTH_YEAR_SECS * 11.862615,
    'saturn'  => EARTH_YEAR_SECS * 29.447498,
    'uranus'  => EARTH_YEAR_SECS * 84.016846,
    'neptune' => EARTH_YEAR_SECS * 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(method_sym, *arguments, &block)
    found, planet = planet_from_method(method_sym.to_s)
    if found
      age_on planet
    else
      super
    end
  end

  def respond_to_missing?(method_name, include_private = false)
    planet_from_method(method_name)[0] || super
  end

  private

  def age_on(planet)
    (@seconds / YEAR_LENGTH[planet]).round(2)
  end

  def planet_from_method(method_name)
    md = /^on_(.*)$/.match(method_name)
    found = md && YEAR_LENGTH.key?(md[1].to_s)
    [found, found ? md[1].to_s : '']
  end
end

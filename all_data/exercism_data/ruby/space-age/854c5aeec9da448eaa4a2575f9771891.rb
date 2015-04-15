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

# Solution for Exercism.io Ruby Space Age problem.
class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(method_sym, *arguments, &block)
    md = /^on_(.*)$/.match(method_sym.to_s)
    if md && YEAR_LENGTH.key?(md[1].to_s)
      age_on md[1].to_s
    else
      super
    end
  end

  private

  def age_on(planet)
    (@seconds * 100.0 / YEAR_LENGTH[planet]).round / 100.0
  end
end

class SpaceAge
  EARTH_YEAR = 31_557_600
  PLANET_YEAR_FACTOR = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(method_name)
    planet = parse_method(method_name)
    years_on(planet) if PLANET_YEAR_FACTOR.key?(planet)
  end

  private

  def parse_method(method_name)
    method_name.to_s.split("_").last.to_sym
  end

  def years_on(planet)
    seconds.fdiv(EARTH_YEAR * PLANET_YEAR_FACTOR[planet]).round(2)
  end
end

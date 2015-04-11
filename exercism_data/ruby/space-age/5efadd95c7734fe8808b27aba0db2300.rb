class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31557600 
  PLANET_YEARS = {
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds.to_f
  end

  def on_earth
    (time_on_earth).round(2)
  end

  def time_on_earth
    @seconds / EARTH_YEAR_IN_SECONDS
  end

  def method_missing(method_sym,*args)
    match = method_sym.to_s.match(/on_(.*)/)
    on(match[1].to_sym)
  end

  def on (planet)
    raise ArgumentError unless PLANET_YEARS[planet]
    (time_on_earth / PLANET_YEARS[planet]).round(2)
  end
end

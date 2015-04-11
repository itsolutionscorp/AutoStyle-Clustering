class SpaceAge
  EARTH_YEAR = 31557600 # seconds
  PLANET_SCALES = {
    earth:   1.0,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }.freeze

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(name, *args, &blk)
    if name =~ /^on_([a-z]+)$/
      age_on_planet($1.to_sym)
    else
      super
    end
  end

  def age_on_planet(planet)
    earth_years = seconds.fdiv(EARTH_YEAR)
    (earth_years / PLANET_SCALES[planet]).round(2)
  end
end

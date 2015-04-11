class SpaceAge
  EARTH_SECONDS = 31_557_600

  PLANET_YEAR_FACTORS = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  # define on_planet methods
  PLANET_YEAR_FACTORS.keys.each do |planet|
    define_method("on_#{planet}") { years_for_planet(planet).round(2) }
  end

  private

  def years_for_planet(planet)
    factor = PLANET_YEAR_FACTORS[planet]
    seconds / (EARTH_SECONDS * factor).to_f
  end
end

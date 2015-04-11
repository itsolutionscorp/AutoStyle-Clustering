class SpaceAge < Struct.new(:seconds)

  EARTH_YEAR = 365.25 * 24 * 60 * 60

  PLANETS_YEARS = {
    earth:   EARTH_YEAR,
    mercury: 0.2408467 * EARTH_YEAR,
    venus:   0.61519726 * EARTH_YEAR,
    mars:    1.8808158 * EARTH_YEAR,
    jupiter: 11.862615 * EARTH_YEAR,
    saturn:  29.447498 * EARTH_YEAR,
    uranus:  84.016846 * EARTH_YEAR,
    neptune: 164.79132 * EARTH_YEAR
  }

  PLANETS_YEARS.each do |planet, planet_year|
    define_method("on_#{planet}") do
      (seconds / planet_year).round(2)
    end
  end
end

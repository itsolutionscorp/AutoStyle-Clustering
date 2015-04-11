class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  planets_orbital_period_in_earth_years = {
    mercury:  0.2408467,
    venus:    0.61519726,
    earth:    1,
    mars:     1.8808158,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune: 164.79132,
  }

  planets_orbital_period_in_earth_years.each do |planet, orbital_period_in_earth_years|

    define_method "on_#{planet}" do
      age_in_planet_years orbital_period_in_earth_years
    end

  end

  private

  ONE_EARTH_YEAR_IN_SECONDS = 31557600.0

  def age_in_planet_years orbital_period_in_earth_years
    (seconds_to_earth_years / orbital_period_in_earth_years).round(2)
  end

  def seconds_to_earth_years
    seconds / ONE_EARTH_YEAR_IN_SECONDS
  end
end

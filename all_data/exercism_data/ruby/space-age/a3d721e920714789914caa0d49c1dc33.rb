class SpaceAge

  SECONDS_IN_EARTH_YEAR = 31557600.0

  SOLAR_SYSTEM = {
    mercury:  0.2408467,
    venus:    0.61519726,
    earth:    1.0,
    mars:     1.8808158,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune:  164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  SOLAR_SYSTEM.each_pair do |planet, orbital_period|
    define_method "on_#{planet}" do
      earth_years = calculate_earth_years_for(orbital_period)
      (seconds / earth_years).round(2)
    end
  end

  private

  def calculate_earth_years_for(orbital_period)
    SECONDS_IN_EARTH_YEAR * orbital_period
  end

end

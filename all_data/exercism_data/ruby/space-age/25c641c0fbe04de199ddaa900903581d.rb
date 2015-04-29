class SpaceAge
  SECONDS_IN_ONE_EARTH_YEAR = 31557600

  attr :seconds

  ORBITAL_PERIODS_IN_EARTH_YEARS = {
    mercury: 0.2408467,
    venus: 0.61519726,
    earth: 1,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_PERIODS_IN_EARTH_YEARS.each do |planet, orbital_period_in_earth_years|
    define_method :"on_#{planet}" do
      calculate_age(orbital_period_in_earth_years)
    end
  end

  private

  def calculate_age(orbital_period_in_earth_years)
    orbital_period_in_seconds = orbital_period_in_earth_years * SECONDS_IN_ONE_EARTH_YEAR
    (seconds.fdiv(orbital_period_in_seconds)).round(2)
  end
end

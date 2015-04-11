class SpaceAge
  DECIMAL_PRECISION = 2
  EARTH_YEAR_IN_SECONDS = 31557600
  ORBITAL_PERIODS = {
    earth: 1.0,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132 }

  attr_reader :seconds

  def initialize(age_in_seconds)
      @seconds = age_in_seconds
  end

  ORBITAL_PERIODS.each do |planet, orbital_period|
    define_method("on_#{planet}") { round(age_in_earth_years / orbital_period) }
  end

  private

  def age_in_earth_years
    @seconds.to_f / EARTH_YEAR_IN_SECONDS
  end

  def round(number)
    number.round(DECIMAL_PRECISION)
  end
end

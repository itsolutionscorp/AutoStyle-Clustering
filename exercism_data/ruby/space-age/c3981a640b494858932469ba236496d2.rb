class SpaceAge
  attr_reader :seconds

  ORBITAL_PERIODS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  SECONDS_IN_EARTH_YEAR = 31557600.0

  ORBITAL_PERIODS.each do |planet, period|
    define_method("on_#{planet}") do
      (age_in_earth_years / period).round(2)
    end
  end

  def initialize(seconds)
    @seconds = seconds
  end

private
  def age_in_earth_years
    seconds / SECONDS_IN_EARTH_YEAR
  end
end

class SpaceAge
  attr :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  orbital_periods_in_earth_years = {
    mercury: 0.2408467,
    venus: 0.61519726,
    earth: 1,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  orbital_periods_in_earth_years.each do |planet, orbital_period_in_earth_years|
    orbital_period_in_seconds = orbital_period_in_earth_years * 31557600
    define_method :"on_#{planet}" do
      (seconds.fdiv(orbital_period_in_seconds)).round(2)
    end
  end
end

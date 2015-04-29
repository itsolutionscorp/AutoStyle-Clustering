class SpaceAge

  SECONDS_ON_EARTH = 31557600.0

  ORBITAL_PERIOD_COEFFICIENTS = {
    earth: 1.0,
    venus: 0.61519726,
    mercury: 0.2408467,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132,
  }

  def initialize(seconds)
    @seconds = seconds
  end

  attr_reader :seconds

  ORBITAL_PERIOD_COEFFICIENTS.keys.each do |planet|
    define_method(:"on_#{planet}") do
      planet_years(planet)
    end
  end

  private

  def planet_years(planet)
    (earth_years / orbital_period_in_earth_years(planet)).round(2)
  end

  def earth_years
    seconds / SECONDS_ON_EARTH
  end

  def orbital_period_in_earth_years(planet)
    ORBITAL_PERIOD_COEFFICIENTS[planet]
  end

end

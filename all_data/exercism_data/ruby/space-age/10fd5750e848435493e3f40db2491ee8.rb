class SpaceAge
  attr_reader :seconds

  def initialize(input_seconds)
    @seconds = input_seconds.to_f
  end

  def on_earth
    planet_orbital = orbital_periods[:earth]
    years(planet_orbital)
  end

  def on_mercury
    planet_orbital = orbital_periods[:mercury]
    years(planet_orbital)
  end

  def on_venus
    planet_orbital = orbital_periods[:venus]
    years(planet_orbital)
  end

  def on_mars
    planet_orbital = orbital_periods[:mars]
    years(planet_orbital)
  end

  def on_jupiter
    planet_orbital = orbital_periods[:jupiter]
    years(planet_orbital)
  end

  def on_saturn
    planet_orbital = orbital_periods[:saturn]
    years(planet_orbital)
  end

  def on_uranus
    planet_orbital = orbital_periods[:uranus]
    years(planet_orbital)
  end

  def on_neptune
    planet_orbital = orbital_periods[:neptune]
    years(planet_orbital)
  end

  private

  attr_reader :orbital_periods

  def earth_seconds
    31557600.to_f
  end

  def years(planet_orbital)
    planet_seconds = planet_orbital * earth_seconds
    (seconds/planet_seconds).round(2)
  end

  def orbital_periods
    @orbital_periods =
      {
      earth: 1,
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132
    }
  end
end

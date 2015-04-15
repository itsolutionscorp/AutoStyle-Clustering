class SpaceAge
  attr_reader :seconds

  def initialize(input_seconds)
    @seconds = input_seconds.to_f
  end

  def on_earth
    years_on(:earth)
  end

  def on_mercury
    years_on(:mercury)
  end

  def on_venus
    years_on(:venus)
  end

  def on_mars
    years_on(:mars)
  end

  def on_jupiter
    years_on(:jupiter)
  end

  def on_saturn
    years_on(:saturn)
  end

  def on_uranus
    years_on(:uranus)
  end

  def on_neptune
    years_on(:neptune)
  end

  private

  attr_reader :orbital_periods

  def years_on(planet)
    planet_orbital = orbital_periods[planet]
    planet_seconds = planet_orbital * earth_seconds
    (seconds/planet_seconds).round(2)
  end

  def earth_seconds
    31557600.to_f
  end

  def orbital_periods
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

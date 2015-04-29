class SpaceAge < Struct.new(:seconds)
  EARTH_YEAR_SECONDS = 31557600
  MERCURY_ORBITAL_PERIOD = 0.2408467
  VENUS_ORBITAL_PERIOD = 0.61519726
  MARS_ORBITAL_PERIOD = 1.8808158
  JUPITER_ORBITAL_PERIOD = 11.862615
  SATURN_ORBITAL_PERIOD = 29.447498
  URANUS_ORBITAL_PERIOD = 84.016846
  NEPTUNE_ORBITAL_PERIOD = 164.79132

  def on_earth
    convert
  end

  def on_mercury
    convert(MERCURY_ORBITAL_PERIOD)
  end

  def on_venus
    convert(VENUS_ORBITAL_PERIOD)
  end

  def on_mars
    convert(MARS_ORBITAL_PERIOD)
  end

  def on_jupiter
    convert(JUPITER_ORBITAL_PERIOD)
  end

  def on_saturn
    convert(SATURN_ORBITAL_PERIOD)
  end

  def on_uranus
    convert(URANUS_ORBITAL_PERIOD)
  end

  def on_neptune
    convert(NEPTUNE_ORBITAL_PERIOD)
  end

  private

  def convert(orbital_period = 1)
    (seconds.to_f / EARTH_YEAR_SECONDS / orbital_period).round(2)
  end
end

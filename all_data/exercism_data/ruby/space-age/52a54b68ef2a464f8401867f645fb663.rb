class SpaceAge

  EARTH   = 365.25
  MERCURY = 0.2408467
  VENUS   = 0.61519726
  MARS    = 1.8808158
  JUPITER = 11.862615
  SATURN  = 29.447498
  URANUS  = 84.016846
  NEPTUNE = 164.79132

  def initialize(age)
    @age = age
  end

  def seconds
    @age
  end

  def format(planet)
    ((@age / (planet * 86400) / EARTH) * 100).round / 100.0
  end

  def on_earth
    (@age / (EARTH * 86400) * 100).round / 100.0
  end

  def on_mercury
    format(MERCURY)
  end

  def on_venus
    format(VENUS)
  end

  def on_mars
    format(MARS)
  end

  def on_jupiter
    format(JUPITER)
  end

  def on_saturn
    format(SATURN)
  end

  def on_uranus
    format(URANUS)
  end

  def on_neptune
    format(NEPTUNE)
  end
end

class SpaceAge
  SECONDS_PER_DAY = 86_400
  EARTH_SECONDS = 31557600
  EARTH_DAYS = 365.25
  MERCURY = 0.2408467
  VENUS = 0.61519726
  MARS = 1.8808158
  JUPITER = 11.862615
  SATURN = 29.447498
  URANUS = 84.016846
  NEPTUNE = 164.79132

  def initialize age
    @age = age
  end

  def seconds
    @age
  end

  def on_earth
    eval_age(EARTH_SECONDS)
  end

  def on_mercury
    eval_age(planet_seconds(MERCURY))
  end

  def on_venus
    eval_age(planet_seconds(VENUS))
  end

  def on_mars
    eval_age(planet_seconds(MARS))
  end

  def on_jupiter
    eval_age(planet_seconds(JUPITER))
  end

  def on_saturn
    eval_age(planet_seconds(SATURN))
  end

  def on_uranus
    eval_age(planet_seconds(URANUS))
  end

  def on_neptune
    eval_age(planet_seconds(NEPTUNE))
  end

  private

  def eval_age planet
    @age.fdiv(planet).round(2)
  end

  def planet_seconds planet
    planet * EARTH_DAYS * SECONDS_PER_DAY
  end
end

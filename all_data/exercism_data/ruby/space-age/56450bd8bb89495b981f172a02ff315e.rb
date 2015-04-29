class SpaceAge

  def initialize age_in_seconds
    @age_in_seconds = age_in_seconds
    @age_on_earth   = age_in_seconds.fdiv 31557600
  end

  def seconds
    @age_in_seconds
  end

  def on_earth
    @age_on_earth.round 2
  end

  def on_mercury
    calc_age 0.2408467
  end

  def on_venus
    calc_age 0.61519726
  end

  def on_mars
    calc_age 1.8808158
  end

  def on_jupiter
    calc_age 11.862615
  end

  def on_saturn
    calc_age 29.447498
  end

  def on_uranus
    calc_age 84.016846
  end

  def on_neptune
    calc_age 164.79132
  end

  def calc_age orbit_in_earth_years
    @age_on_earth.fdiv(orbit_in_earth_years).round 2
  end

end

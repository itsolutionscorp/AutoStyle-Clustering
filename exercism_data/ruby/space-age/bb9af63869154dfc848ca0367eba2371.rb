class SpaceAge

YEAR = { earth =>  31557600, 
  mercury => (0.2408467 * EARTH_YEAR), 
  venus => (0.61519726 * EARTH_YEAR),
  mars => (1.8808158 * EARTH_YEAR),
  jupiter => (11.862615 * EARTH_YEAR),
  saturn => (29.447498 * EARTH_YEAR),
  uranus => (84.016846 * EARTH_YEAR),
  neptune => (164.79132 * EARTH_YEAR)
}


  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def seconds
    @seconds
  end

  def on_earth
    (@seconds / EARTH_YEAR).round(2)
  end

  def on_mercury
    (@seconds / YEAR[earth].round(2)
  end

  def on_venus
    (@seconds / (0.61519726 * EARTH_YEAR)).round(2)
  end

  def on_mars
    (@seconds / (1.8808158 * EARTH_YEAR)).round(2)
  end

  def on_jupiter
    (@seconds / (11.862615 * EARTH_YEAR)).round(2)
  end

  def on_saturn
    (@seconds / (29.447498 * EARTH_YEAR)).round(2)
  end

  def on_uranus
    (@seconds / (84.016846 * EARTH_YEAR)).round(2)
  end

  def on_neptune
    (@seconds / (164.79132 * EARTH_YEAR)).round(2)
  end
end

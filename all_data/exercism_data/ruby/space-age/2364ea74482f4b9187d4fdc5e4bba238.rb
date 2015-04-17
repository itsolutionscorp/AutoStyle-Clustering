class SpaceAge
  # one solar year
  EARTH_YEAR = 365.25
  EARTH_SEC = EARTH_YEAR * 24 * 60 * 60.0

  def initialize seconds
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    (@seconds / EARTH_SEC).round(2)
  end

  def on_mercury
    (@seconds / (0.2408467 * EARTH_SEC)).round(2)
  end

  def on_venus
    (@seconds / (0.61519726 * EARTH_SEC)).round(2)
  end

  def on_mars
    (@seconds / (1.8808158 * EARTH_SEC)).round(2)
  end

  def on_jupiter
    (@seconds / (11.862615 * EARTH_SEC)).round(2)
  end

  def on_saturn
    (@seconds / (29.447498 * EARTH_SEC)).round(2)
  end

  def on_uranus
    (@seconds / (84.016846 * EARTH_SEC)).round(2)
  end

  def on_neptune
    (@seconds / (164.79132 * EARTH_SEC)).round(2)
  end
end
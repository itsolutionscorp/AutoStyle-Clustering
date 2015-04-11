class SpaceAge
  EARTH = 31557600
  MERCURY = 0.2408467
  VENUS = 0.61519726
  MARS = 1.8808158
  JUPITER = 11.862615
  SATURN = 29.447498
  URANUS = 84.016846
  NEPTUNE = 164.79132

  attr_reader :seconds

  def initialize(n)
    @seconds = n.to_f
  end

  def on_earth
    (seconds / EARTH).round(2)
  end

  def on_mercury
    (on_earth / MERCURY).round(2)
  end

  def on_venus
    ((seconds / EARTH) / VENUS).round(2)
  end

  def on_mars
    (on_earth / MARS).round(2)
  end

  def on_jupiter
    (on_earth / JUPITER).round(2)
  end

  def on_saturn
    (on_earth / SATURN).round(2)
  end

  def on_uranus
    (on_earth / URANUS).round(2)
  end

  def on_neptune
    (on_earth / NEPTUNE).round(2)
  end
end

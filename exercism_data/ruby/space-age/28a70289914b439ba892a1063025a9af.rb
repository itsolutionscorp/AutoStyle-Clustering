class SpaceAge
  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    convert_age(1)
  end

  def on_mercury
    convert_age(0.2408467)
  end

  def on_venus
    convert_age(0.61519726)
  end

  def on_mars
    convert_age(1.8808158)
  end

  def on_jupiter
    convert_age(11.862615)
  end

  def on_saturn
    convert_age(29.447498)
  end

  def on_uranus
    convert_age(84.016846)
  end

  def on_neptune
    convert_age(164.79132)
  end

  private
  def convert_age(from)
    (@seconds / (31557600.0 * from)).round(2)
  end

end

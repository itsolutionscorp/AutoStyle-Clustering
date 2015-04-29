class SpaceAge
  def initialize(age)
    @age = age
    @year = 60 * 60 * 24 * 365.25
  end

  def seconds
    return @age
  end

  def on_earth
    convert(1)
  end

  def on_mercury
    convert(0.2408467)
  end

  def on_venus
    convert(0.61519726)
  end

  def on_mars
    convert(1.8808158)
  end

  def on_jupiter
    convert(11.862615)
  end

  def on_saturn
    convert(29.447498)
  end

  def on_uranus
    convert(84.016846)
  end

  def on_neptune
    convert(164.79132)
  end

  def convert(num)
    return (@age / (num * @year).to_f).round(2)
  end
end

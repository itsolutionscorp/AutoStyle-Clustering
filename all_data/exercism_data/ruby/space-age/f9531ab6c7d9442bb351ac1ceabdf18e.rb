class SpaceAge
  def initialize(time)
    @time = time
    @earth_years = @time / 31557600.0
  end
  
  def seconds
    @time
  end

  def on_earth
    @earth_years.round(2)
  end

  def on_mercury
    converter(0.2408467)
  end

  def on_venus
    converter(0.61519726)
  end

  def on_mars
    converter(1.8808158)
  end

  def on_jupiter
    converter(11.862615)
  end

  def on_saturn
  converter(29.447498)
  end

  def on_uranus
    converter(84.016846)
  end

  def on_neptune
  converter(164.79132)
  end

  private
  
  def converter(ratio)
    (@earth_years / ratio).round(2)
  end
end

#p SpaceAge.new(1).on_mercury

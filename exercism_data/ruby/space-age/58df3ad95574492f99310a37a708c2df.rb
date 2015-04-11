class SpaceAge
  def initialize(time)
    @time = time
  end
  
  def seconds
    @time
  end

  def on_earth
    years = @time / 31557600.0
    years.round(2)
  end

  def on_mercury
    years = (@time / 31557600.0) / 0.2408467
    years.round(2)
  end

  def on_venus
    years = (@time / 31557600.0) / 0.61519726
    years.round(2)
  end

  def on_mars
    years = (@time / 31557600.0) / 1.8808158
    years.round(2)
  end

  def on_jupiter
    years = (@time / 31557600.0) / 11.862615
    years.round(2)
  end

  def on_saturn
    years = (@time / 31557600.0) / 29.447498
    years.round(2)
  end

  def on_uranus
    years = (@time / 31557600.0) / 84.016846
    years.round(2)
  end

  def on_neptune
     @years = (@time / 31557600.0) / 164.79132
     round
  end

  private

  def round
    @years.round(2)
  end
end

#age = SpaceAge.new(1_000_000_000)
#age.on_earth

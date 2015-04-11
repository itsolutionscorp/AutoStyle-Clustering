class SpaceAge
  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def on_earth
    sprintf('%.2f', (seconds / 31557600)).to_f
  end

  def on_mercury
    sprintf('%.2f', (on_earth / 0.2408467)).to_f
  end

  def on_venus
    sprintf('%.2f', (on_earth/0.61519726)).to_f
  end

  def on_mars
    sprintf('%.2f', (on_earth/1.8808158)).to_f
  end

  def on_jupiter
    sprintf('%.2f', (on_earth/11.862615)).to_f
  end

  def on_saturn
    sprintf('%.2f', (on_earth/29.447498)).to_f
  end

  def on_uranus
    sprintf('%.2f', (on_earth/84.016846)).to_f
  end

  def on_neptune
    sprintf('%.2f', (on_earth/164.79132)).to_f
  end
end

class SpaceAge
  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    format_seconds(@seconds / 31557600.00)
  end

  def on_mercury
    format_seconds(on_earth / 0.2408467)
  end

  def on_venus
    format_seconds(on_earth / 0.61519726)
  end

  def on_mars
    format_seconds(on_earth / 1.8808158)
  end

  def on_jupiter
    format_seconds(on_earth / 11.862615)
  end

  def on_saturn
    format_seconds(on_earth / 29.447498)
  end

  def on_uranus
    format_seconds(on_earth / 84.016846)
  end

  def on_neptune
    format_seconds(on_earth / 164.79132)
  end

  private

  def format_seconds(seconds)
    seconds.round(2)
  end
end

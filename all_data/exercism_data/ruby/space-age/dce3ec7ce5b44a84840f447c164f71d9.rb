class SpaceAge
  def initialize(seconds)
    @seconds = seconds
  end

  attr_reader :seconds

  def on_earth
    earth(1)
  end

  def on_mercury
    earth(0.2408467)
  end

  def on_venus
    earth(0.61519726)
  end

  def on_mars
    earth(1.8808158)
  end

  def on_jupiter
    earth(11.862615)
  end

  def on_saturn
    earth(29.447498)
  end

  def on_uranus
    earth(84.016846)
  end

  def on_neptune
    earth(164.79132)
  end

  private

  def earth(orbital)
    ((seconds/(31557600.0*orbital)).round(2)
  end
end

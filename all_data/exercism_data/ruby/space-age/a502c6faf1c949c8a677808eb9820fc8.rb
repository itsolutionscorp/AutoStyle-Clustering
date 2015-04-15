class SpaceAge
  def initialize(seconds)
    @seconds = seconds
    @earth = (seconds/31557600.0)
  end

  attr_reader :seconds

  def on_earth
    @earth.round(2)
  end

  def on_mercury
    (earth/0.2408467).round(2)
  end

  def on_venus
    (earth/0.61519726).round(2)
  end

  def on_mars
    (earth/1.8808158).round(2)
  end

  def on_jupiter
    (earth/11.862615).round(2)
  end

  def on_saturn
    (earth/29.447498).round(2)
  end

  def on_uranus
    (earth/84.016846).round(2)
  end

  def on_neptune
    (earth/164.79132).round(2)
  end

  private
  attr_reader :earth
end

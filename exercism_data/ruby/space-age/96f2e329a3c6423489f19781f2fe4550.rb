class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    unrounded_earth_age.round(2)
  end

  def on_mercury
    (unrounded_earth_age / 0.2408467).round(2)
  end

  def on_venus
    (unrounded_earth_age / 0.61519726).round(2)
  end

  def on_mars
    (unrounded_earth_age / 1.8808158).round(2)
  end

  def on_jupiter
    (unrounded_earth_age / 11.862615).round(2)
  end

  def on_saturn
    (unrounded_earth_age / 29.447498).round(2)
  end

  def on_uranus
    (unrounded_earth_age / 84.016846).round(2)
  end

  def on_neptune
    (unrounded_earth_age / 164.79132).round(2)
  end

  private

  def unrounded_earth_age
    @seconds / 31557600.0
  end
end

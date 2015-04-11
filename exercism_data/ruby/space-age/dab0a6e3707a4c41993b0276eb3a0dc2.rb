class SpaceAge
  def initialize seconds
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def age_on_earth
    (@seconds / 31_557_600.0)
  end

  def on_earth
    age_on_earth.round(2)
  end

  def on_mercury
    (age_on_earth / 0.2408467).round(2)
  end

  def on_venus
    (age_on_earth / 0.61519726).round(2)
  end

  def on_mars
    (age_on_earth / 1.8808158).round(2)
  end

  def on_jupiter
    (age_on_earth / 11.862615).round(2)
  end

  def on_saturn
    (age_on_earth / 29.447498).round(2)
  end

  def on_uranus
    (age_on_earth / 84.016846).round(2)
  end

  def on_neptune
    (age_on_earth / 164.79132).round(2)
  end
end

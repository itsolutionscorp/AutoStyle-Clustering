class SpaceAge
  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    earth_years.round(2)
  end

  def on_mercury
    (earth_years / 0.2408467).round(2)
  end

  def on_venus
    (earth_years / 0.61519726).round(2)
  end

  def on_mars
    (earth_years / 1.8808158).round(2)
  end

  def on_jupiter
    (earth_years / 11.862615).round(2)
  end

  def on_saturn
    (earth_years / 29.447498).round(2)
  end

  def on_uranus
    (earth_years / 84.016846).round(2)
  end

  def on_neptune
    (earth_years / 164.79132).round(2)
  end

  private

  def earth_years
    @seconds / 31557600.0
  end
end
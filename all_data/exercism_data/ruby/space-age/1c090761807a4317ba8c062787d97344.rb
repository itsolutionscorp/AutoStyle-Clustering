class SpaceAge
  def initialize(input)
    @input = input
    @earth_day_seconds = 31_557_600.00
  end

  def seconds
    @input
  end

  def on_earth
    (@input / @earth_day_seconds).round(2)
  end

  def on_mercury
    (@input / @earth_day_seconds / 0.2408467).round(2)
  end

  def on_venus
    (@input / @earth_day_seconds / 0.61519726).round(2)
  end

  def on_mars
    (@input / @earth_day_seconds / 1.8808158).round(2)
  end

  def on_jupiter
    (@input / @earth_day_seconds / 11.862615).round(2)
  end

  def on_saturn
    (@input / @earth_day_seconds / 29.447498).round(2)
  end

  def on_uranus
    (@input / @earth_day_seconds / 84.016846).round(2)
  end

  def on_neptune
    (@input / @earth_day_seconds / 164.79132).round(2)
  end
end

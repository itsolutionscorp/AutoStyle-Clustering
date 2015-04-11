class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
    @earth_seconds = seconds.to_f / 31557600
  end

  def on_earth
    @earth_seconds.round(2)
  end

  def on_mercury
    calc_years(0.2408467)
  end

  def on_venus
    calc_years(0.61519726)
  end

  def on_mars
    calc_years(1.8808158)
  end

  def on_jupiter
    calc_years(11.862615)
  end

  def on_saturn
    calc_years(29.447498)
  end

  def on_uranus
    calc_years(84.016846)
  end

  def on_neptune
    calc_years(164.79132)
  end

  def calc_years(years)
    (@earth_seconds / years).round(2)
  end
end

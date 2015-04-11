class SpaceAge
  def initialize(*args)
    @seconds = args
  end

  def seconds
    @seconds.first
  end

  def earth_year
    365.25
  end

  def on_earth
    (seconds_to_years / earth_year).round(2)
  end

  def on_mercury
    (seconds_to_years / (earth_year * 0.2408467)).round(2)
  end

  def on_venus
    (seconds_to_years / (earth_year * 0.61519726)).round(2)
  end

  def on_mars
    (seconds_to_years / (earth_year * 1.8808158)).round(2)
  end

  def on_jupiter
    (seconds_to_years / (earth_year * 11.862615)).round(2)
  end

  def on_saturn
    (seconds_to_years / (earth_year * 29.447498)).round(2)
  end

  def on_uranus
    (seconds_to_years / (earth_year * 84.016846)).round(2)
  end

  def on_neptune
    (seconds_to_years / (earth_year * 164.79132)).round(2)
  end

  def seconds_to_years
    (((@seconds.first / 60) / 60) / 24)
  end
end

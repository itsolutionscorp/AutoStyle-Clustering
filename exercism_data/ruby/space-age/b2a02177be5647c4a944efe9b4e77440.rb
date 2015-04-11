class SpaceAge

  EARTH_YEAR = 31557600
  MERCURY_YEAR = 7600543.81992
  VENUS_YEAR = 19414149.052176
  MARS_YEAR = 59354032.6901
  JUPITER_YEAR = 374355659.124
  SATURN_YEAR = 929292362.885
  URANUS_YEAR = 2651370019.33
  NEPTUNE_YEAR = 5200417928.88

  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    (seconds.to_f / EARTH_YEAR.to_f).round(2)
  end

  def on_mercury
    (seconds.to_f / MERCURY_YEAR.to_f).round(2)
  end

  def on_venus
    (seconds.to_f / VENUS_YEAR.to_f).round(2)
  end

  def on_mars
    (seconds.to_f / MARS_YEAR.to_f).round(2)
  end

  def on_jupiter
    (seconds.to_f / JUPITER_YEAR.to_f).round(2)
  end

  def on_saturn
    (seconds.to_f / SATURN_YEAR.to_f).round(2)
  end

  def on_uranus
    (seconds.to_f / URANUS_YEAR.to_f).round(2)
  end

  def on_neptune
    (seconds.to_f / NEPTUNE_YEAR.to_f).round(2)
  end

end

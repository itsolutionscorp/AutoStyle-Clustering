class SpaceAge

  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    solar_year_conversion(1)
  end

  def on_mercury
    solar_year_conversion(0.2408467)
  end

  def on_venus
    solar_year_conversion(0.61519726)
  end

  def on_mars
    solar_year_conversion(1.8808158)
  end

  def on_jupiter
    solar_year_conversion(11.862615)
  end

  def on_saturn
    solar_year_conversion(29.447498)
  end

  def on_uranus
    solar_year_conversion(84.016846)
  end

  def on_neptune
    solar_year_conversion(164.79132)
  end

  private
  def solar_year_conversion(orbital_period)
    ((@seconds / 31_557_600.to_f) / orbital_period ).round(2)
  end
end

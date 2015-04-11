class SpaceAge
  attr_reader :seconds
  
  @@earth_year_seconds = 31_557_600
  def initialize(seconds)
    @seconds = seconds
  end

  def years_by_mult(mult=1.0)
    (@seconds / (@@earth_year_seconds * mult)).round 2
  end

  def on_earth
    years_by_mult
  end

  def on_mercury
    years_by_mult(0.2408467)
  end
  
  def on_venus
    years_by_mult(0.61519726)
  end
  
  def on_mars
    years_by_mult(1.8808158)
  end
  
  def on_jupiter
    years_by_mult(11.862615)
  end
  
  def on_saturn
    years_by_mult(29.447498)
  end
  
  def on_uranus
    years_by_mult(84.016846)
  end
  
  def on_neptune
    years_by_mult(164.79132)
  end
end

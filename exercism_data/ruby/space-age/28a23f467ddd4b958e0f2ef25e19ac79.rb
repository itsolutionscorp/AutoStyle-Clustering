class SpaceAge
  attr_reader :seconds
  
  @@earth_year_seconds = 31_557_600
  @@year_constants = {:on_earth => 1.0,
                      :on_mercury => 0.2408467,
                      :on_venus => 0.61519726,
                      :on_mars => 1.8808158,
                      :on_jupiter => 11.862615,
                      :on_saturn => 29.447498,
                      :on_uranus => 84.016846,
                      :on_neptune => 164.79132}

  def initialize(seconds)
    @seconds = seconds
  end

  def years_by_mult(mult=1.0)
    (@seconds / (@@earth_year_seconds * mult)).round 2
  end

  def method_missing(m, *args, &block)
    if @@year_constants.include? m
      years_by_mult @@year_constants[m]
    else
      raise NoMethodError
    end
  end

end

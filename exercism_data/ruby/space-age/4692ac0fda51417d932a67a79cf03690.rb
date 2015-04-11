class SpaceAge
  attr_reader :seconds
  
  EARTH_YEAR_SECONDS = 31_557_600
  YEAR_CONSTANTS = {:on_earth => 1.0,
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
    (@seconds / (EARTH_YEAR_SECONDS * mult)).round 2
  end

  def method_missing(m, *args, &block)
    if YEAR_CONSTANTS.include? m
      self.class.class_eval do
        define_method m do
          years_by_mult YEAR_CONSTANTS[m]
        end
      end
      self.send m
    else
      super
    end
  end

end

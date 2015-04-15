class SpaceAge

  attr_reader :seconds

  EARTH_YR    = 31557600.0
  RATIO_TO_EARTH = {
    mercury: 0.2408467,
    venus: 0.61519726,
    earth: 1.0,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  RATIO_TO_EARTH.keys.each do |planet|
    define_method("on_#{planet}") do
      (@seconds/(RATIO_TO_EARTH[planet]*EARTH_YR)).round(2)
    end
  end

  def method_missing(m, *args, &block)  
    raise "#{m} method Not Found on class #{self.class}"
  end

  def initialize(seconds)
    @seconds = seconds
  end

end

class SpaceAge
  EARTH_YEAR = 31_557_600
  YEAR_LENGTH = {
    mercury: EARTH_YEAR * 0.2408467,
    venus: EARTH_YEAR * 0.61519726,
    earth: EARTH_YEAR,
    mars: EARTH_YEAR * 1.8808158,
    jupiter: EARTH_YEAR * 11.862615,
    saturn: EARTH_YEAR * 29.447498,
    uranus: EARTH_YEAR * 84.016846,
    neptune: EARTH_YEAR * 164.79132
  }
  YEAR_LENGTH.each_key do |planet|
    define_method("on_#{planet}") { solar_age planet }
  end
  
  attr_reader :seconds
  def initialize seconds
    @seconds = seconds
  end
  
  private
  def solar_age planet, seconds = seconds
    (seconds.to_f / YEAR_LENGTH[planet]).round(2)
  end
end

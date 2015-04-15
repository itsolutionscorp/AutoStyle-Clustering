class SpaceAge
  EARTH_YEAR = 31_557_600
  YEAR_LENGTH = {
    mercury: EARTH_YEAR * 0.2408467,
    venus: EARTH_YEAR * 0.61519726,
    earth: EARTH_YEAR * 1.0,
    mars: EARTH_YEAR * 1.8808158,
    ceres: EARTH_YEAR * 4.60,
    jupiter: EARTH_YEAR * 11.862615,
    saturn: EARTH_YEAR * 29.447498,
    uranus: EARTH_YEAR * 84.016846,
    neptune: EARTH_YEAR * 164.79132,
    pluto: EARTH_YEAR * 247.68
  }.each do |space_object,orbital_length|
    define_method("on_#{space_object}") { orbital_years orbital_length }
  end
  
  attr_reader :seconds
  def initialize seconds
    @seconds = seconds
  end
  
  private
  def orbital_years orbital_length, seconds = seconds
    (seconds.to_f / orbital_length).round(2)
  end
end

class SpaceAge
  attr_reader :seconds
  SECS_PER_EARTH_YEAR = 31557600.0
  RATIOS = {
    "on_earth"    =>  1.0,
    "on_mercury"  =>  0.2408467,
    "on_venus"    =>  0.61519726,
    "on_mars"     =>  1.8808158,
    "on_jupiter"  =>  11.862615,
    "on_saturn"   =>  29.447498,
    "on_uranus"   =>  84.016846,
    "on_neptune"  =>  164.79132
  }
  
  RATIOS.each do |planet, ratio|
    define_method(planet) do
      (@seconds / SECS_PER_EARTH_YEAR / ratio).round(2)
    end
  end
  
  def initialize seconds
    @seconds = seconds
  end
end

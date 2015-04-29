class SpaceAge

  EARTH_YR    = 31557600.0
  MERCURY_YR  = 0.2408467  * EARTH_YR
  VENUS_YR    = 0.61519726 * EARTH_YR
  MARS_YR     = 1.8808158  * EARTH_YR
  JUPITER_YR  = 11.862615  * EARTH_YR
  SATURN_YR   = 29.447498  * EARTH_YR
  URANUS_YR   = 84.016846  * EARTH_YR
  NEPTUNE_YR  = 164.79132  * EARTH_YR

  def initialize(seconds)
    @seconds = seconds
  end
  
  def seconds
    @seconds
  end
  
  def method_missing(m, *args, &block)  
    planet_yr = m.to_s.split("on_")[1].upcase+"_YR"
    (@seconds/SpaceAge.const_get(planet_yr)).round(2)
  end

end

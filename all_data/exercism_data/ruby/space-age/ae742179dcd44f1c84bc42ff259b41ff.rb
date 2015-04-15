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
  def method_missing(m, *args)
      str = "Called #{m} with #{args.inspect}"
  end
  # def on_mercury
  #   (@seconds/MERCURY_YR).round(2)
  # end
  # def on_venus
  #   (@seconds/VENUS_YR).round(2)
  # end
  # def on_earth
  #   (@seconds/EARTH_YR).round(2)
  # end
  # def on_mars
  #   (@seconds/MARS_YR).round(2)
  # end
  # def on_jupiter
  #   (@seconds/JUPITER_YR).round(2)
  # end
  # def on_saturn
  #   (@seconds/SATURN_YR).round(2)
  # end
  # def on_uranus
  #   (@seconds/URANUS_YR).round(2)
  # end
  # def on_neptune
  #   (@seconds/NEPTUNE_YR).round(2)
  # end

end

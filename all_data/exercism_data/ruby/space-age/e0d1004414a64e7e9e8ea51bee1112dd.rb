class SpaceAge
  EARTH_ORBITAL_PERIOD = 31557600.0  #sec
  PLANET_PERIOD_RATIOS = {mercury: 0.2408467, venus: 0.61519726, earth: 1.0, 
                          mars: 1.8808158, jupiter: 11.862615, saturn: 29.447498, 
                          uranus: 84.016846, neptune:164.79132}

  PLANET_PERIOD_RATIOS.each do |planet, ratio|
    define_method("on_#{planet}") do
      ( seconds/(ratio * EARTH_ORBITAL_PERIOD) ).round 2
    end
  end

  attr_reader :seconds
  def initialize(seconds = 0)
    @seconds = seconds
    end
end

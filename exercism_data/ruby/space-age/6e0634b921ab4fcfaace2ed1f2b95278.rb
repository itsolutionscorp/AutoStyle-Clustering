class SpaceAge
  attr_accessor :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  EARTH_ORBITAL_PERIOD = 31557600.0
  PLANET_PERIODS = {
    earth: EARTH_ORBITAL_PERIOD,
    mercury: EARTH_ORBITAL_PERIOD * 0.2408467,
    venus: EARTH_ORBITAL_PERIOD * 0.61519726,
    mars: EARTH_ORBITAL_PERIOD * 1.8808158,
    jupiter: EARTH_ORBITAL_PERIOD * 11.862615,
    saturn: EARTH_ORBITAL_PERIOD * 29.447498,
    uranus: EARTH_ORBITAL_PERIOD * 84.016846,
    neptune: EARTH_ORBITAL_PERIOD * 164.79132
  }.each do |planet, period|
    define_method("on_#{planet.to_s}") do
      ( seconds / period ).round 2
    end
  end
end

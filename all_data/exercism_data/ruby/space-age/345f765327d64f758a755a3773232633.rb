class SpaceAge
  attr_accessor :seconds

  EARTH_ORBITAL_PERIOD_SECONDS = 86_400 * 365.25
  PLANETS_ORBITAL_PERIOD = {
    earth: EARTH_ORBITAL_PERIOD_SECONDS,
    mercury: EARTH_ORBITAL_PERIOD_SECONDS * 0.2408467,
    venus: EARTH_ORBITAL_PERIOD_SECONDS * 0.61519726,
    mars: EARTH_ORBITAL_PERIOD_SECONDS * 1.8808158,
    jupiter: EARTH_ORBITAL_PERIOD_SECONDS * 11.862615,
    saturn: EARTH_ORBITAL_PERIOD_SECONDS * 29.447498,
    uranus: EARTH_ORBITAL_PERIOD_SECONDS * 84.016846,
    neptune: EARTH_ORBITAL_PERIOD_SECONDS * 164.79132 }

  def initialize seconds
    @seconds = seconds
  end

  def method_missing method
    planet = method[3..-1].to_sym
    (@seconds / PLANETS_ORBITAL_PERIOD[planet]).round 2
  end

end

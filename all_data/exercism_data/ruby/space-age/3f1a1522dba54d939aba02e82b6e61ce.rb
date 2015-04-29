class SpaceAge
  attr_accessor :seconds

  EARTH_ORBITAL_PERIOD_SECONDS = 86_400 * 365.25
  PLANETS_ORBITAL_PERIOD = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132 }

  def initialize seconds
    @seconds = seconds
  end

  private

  def method_missing name
    age_on_planet(name[3..-1]) if name.to_s.start_with?("on_")
  end

  def age_on_planet planet
    (@seconds / orbital_period(planet)).round 2
  end

  def orbital_period planet
    PLANETS_ORBITAL_PERIOD[planet.to_sym] * EARTH_ORBITAL_PERIOD_SECONDS
  end

end

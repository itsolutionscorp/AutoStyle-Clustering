class SpaceAge < Struct.new(:seconds)

  EARTH_YEAR_SECONDS = 365.25 * 24 * 60 * 60

  PLANETS_ORBITAL_PERIODS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132,
  }

  PLANETS_ORBITAL_PERIODS.keys.each do |planet|
    define_method "on_#{planet}" do
      age_on_planet(planet).round(2)
    end
  end

  private

  def age_on_planet planet
    seconds / EARTH_YEAR_SECONDS / PLANETS_ORBITAL_PERIODS.fetch(planet)
  end
end

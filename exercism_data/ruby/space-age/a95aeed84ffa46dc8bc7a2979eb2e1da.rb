class SpaceAge < Struct.new(:seconds)

  EARTH_YEAR_SECONDS = 365.25 * 24 * 3600

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


  for planet in PLANETS_ORBITAL_PERIODS.keys
    eval %(
      def on_#{planet}
        self.class.age_on_planet(seconds, :#{planet})
      end
    )
  end

  def self.age_on_planet seconds, planet
    age_on_planet =
      seconds / EARTH_YEAR_SECONDS / PLANETS_ORBITAL_PERIODS.fetch(planet)
    age_on_planet.round(2)
  end

end

class SpaceAge
  attr_reader :seconds

  SECONDS_IN_A_YEAR_ON_EARTH = 31557600.0

   PLANET_MULTIPLIER = {
      mercury: 0.2408467,
      venus: 0.61519726,
      earth: 1,
      mars: 1.8808158,
      jupiter: 11.862615,
      neptune: 164.79132,
      uranus: 84.016846,
      saturn: 29.447498
    }

  def initialize(seconds)
    @seconds = seconds
  end

  PLANET_MULTIPLIER.keys.each do |planet|
    define_method("on_#{planet}") { calculate(planet) }
  end

  private

  def calculate(planet)
    (seconds / seconds_in_a_year(planet)).round(2)
  end

  def seconds_in_a_year(planet)
    SECONDS_IN_A_YEAR_ON_EARTH * PLANET_MULTIPLIER[planet]
  end
end

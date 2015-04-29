class SpaceAge
  SECONDS_IN_EARTH_YEAR = 31_557_600

  PLANET_ORBITAL_PERIODS_IN_EARTH_YEARS = {
    mercury: 0.2408467,
    venus:   0.61519726,
    earth:   1,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  attr_reader :seconds

  PLANET_ORBITAL_PERIODS_IN_EARTH_YEARS.each do |planet, orbital_period|
    define_method "on_#{planet}" do
      (seconds / SECONDS_IN_EARTH_YEAR / orbital_period).round(2)
    end
  end
end

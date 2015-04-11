# space_age.rb
class SpaceAge
  EARTH_YEAR_IN_SECONDS =  31_557_600
  ORBITAL_PERIODS = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  ORBITAL_PERIODS.keys.each do |planet|
    define_method "on_#{planet}" do
      on(planet)
    end
  end

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def earth_years
    seconds.to_f / EARTH_YEAR_IN_SECONDS
  end

  def on(planet)
    (earth_years / ORBITAL_PERIODS[planet]).round(2)
  end
end

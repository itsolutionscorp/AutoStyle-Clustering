class SpaceAge
  EARTH_YEAR = 31557600 # seconds
  ORBITAL_PERIOD_IN_YEARS = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  ORBITAL_PERIOD_IN_YEARS.each do |planet, orbital_period|
    define_method("on_#{planet}") do
      (years_on_earth / orbital_period).round 2
    end
  end

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def years_on_earth
    seconds / EARTH_YEAR.to_f
  end
end

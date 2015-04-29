class SpaceAge
  SECONDS_PER_EARTH_YEAR = 365.25 * 24 * 60 * 60

  ORBITAL_PERIODS = {
    earth:   1.0,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132,
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_PERIODS.keys.each do |planet|
    define_method("on_#{planet}") do  # def on_earth
      on(planet)                      #   on(:earth)
    end                               # end
  end

  private

  def on(planet)
    (seconds / seconds_per_year(planet)).round(2)
  end

  def seconds_per_year(planet)
    SECONDS_PER_EARTH_YEAR * ORBITAL_PERIODS.fetch(planet)
  end
end

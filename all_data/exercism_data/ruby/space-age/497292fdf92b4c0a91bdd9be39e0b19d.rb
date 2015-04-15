class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31_557_600

  YEAR_LENGTHS_IN_SECONDS = {
    mercury: EARTH_YEAR_IN_SECONDS * 0.2408467,
    venus:   EARTH_YEAR_IN_SECONDS * 0.61519726,
    earth:   EARTH_YEAR_IN_SECONDS * 1.0,
    mars:    EARTH_YEAR_IN_SECONDS * 1.8808158,
    jupiter: EARTH_YEAR_IN_SECONDS * 11.862615,
    saturn:  EARTH_YEAR_IN_SECONDS * 29.447498,
    uranus:  EARTH_YEAR_IN_SECONDS * 84.016846,
    neptune: EARTH_YEAR_IN_SECONDS * 164.79132,
  }

  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  YEAR_LENGTHS_IN_SECONDS .each do |name, year_length|
    define_method("on_#{name}") do
      (seconds / year_length).round(2)
    end
  end
end

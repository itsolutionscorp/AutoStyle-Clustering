class SpaceAge
  attr_reader :seconds
  EARTH_YEAR_IN_SECONDS = 31_557_600
  PLANET_CONVERSIONS = {
    "earth"       => 1,
    "mercury"     => 0.2408467,
    "venus"       => 0.61519726,
    "mars"        => 1.8808158,
    "jupiter"     => 11.862615,
    "saturn"      => 29.447498,
    "uranus"      => 84.016846,
    "neptune"     => 164.79132
  }

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  PLANET_CONVERSIONS.keys.each do |planet|
    define_method("on_#{planet}") do
      years_for(planet).round(2)
    end
  end

  private

  def years_for(planet)
    earth_years / PLANET_CONVERSIONS[planet]
  end

  def earth_years
    seconds / EARTH_YEAR_IN_SECONDS.to_f
  end

end

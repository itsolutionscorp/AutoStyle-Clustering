class SpaceAge
  SECONDS_PER_EARTH_YEAR = 31557600.00
  PLANETS_TO_EARTH_YEARS = {
      "earth"   => 1.0,
      "jupiter" => 11.862615,
      "mars"    => 1.8808158,
      "mercury" => 0.2408467,
      "neptune" => 164.79132,
      "saturn"  => 29.447498,
      "uranus"  => 84.016846,
      "venus"   => 0.61519726
  }

  attr_reader :seconds, :earth_years
  def initialize(age_in_seconds)
    @seconds = age_in_seconds
    @earth_years = age_in_seconds / SECONDS_PER_EARTH_YEAR
  end

  PLANETS_TO_EARTH_YEARS.each do |planet, conversion|
    define_method("on_#{planet}") do
      (earth_years / conversion).round(2)
    end
  end

end

class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  EARTH_YEAR_LOOKUPS =
    { mercury: 0.2408467,
      venus: 0.61519726,
      earth: 1.0,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132 }

  EARTH_YEAR_LOOKUPS.each do |planet, orbital|
    define_method("on_#{planet}") do
      calculate_age(orbital)
    end
  end

  private

  def seconds_in_orbital(orbital)
    orbital * 24 * 60 * 60 * 365.25
  end

  def calculate_age(orbital)
    (seconds / seconds_in_orbital(orbital)).round(2)
  end
end

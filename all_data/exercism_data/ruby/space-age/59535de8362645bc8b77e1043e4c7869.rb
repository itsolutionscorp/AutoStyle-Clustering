class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  EARTH_YEAR_LOOKUPS =
    { :on_mercury => 0.2408467,
      :on_venus => 0.61519726,
      :on_earth => 1.0,
      :on_mars => 1.8808158,
      :on_jupiter => 11.862615,
      :on_saturn => 29.447498,
      :on_uranus => 84.016846,
      :on_neptune => 164.79132 }

  EARTH_YEAR_LOOKUPS.each do |planet, orbital|
    define_method(planet) do
      calculate_age(EARTH_YEAR_LOOKUPS[planet])
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

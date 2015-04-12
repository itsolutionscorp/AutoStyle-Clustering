class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def method_missing(planet)
    calculate_age(earth_years_lookup[planet])
  end

  def earth_years_lookup
    { :on_mercury => 0.2408467,
      :on_venus => 0.61519726,
      :on_earth => 1,
      :on_mars => 1.8808158,
      :on_jupiter => 11.862615,
      :on_saturn => 29.447498,
      :on_uranus => 84.016846,
      :on_neptune => 164.79132 }
  end

  private

  def seconds_in_orbital(orbital)
    orbital * 24 * 60 * 60
  end

  def calculate_age(orbital)
    (seconds / seconds_in_orbital(orbital * 365.25)).round(2)
  end
end
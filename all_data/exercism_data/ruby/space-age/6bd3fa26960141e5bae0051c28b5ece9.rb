class SpaceAge

  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

private

  EarthYearInSeconds = 31_557_600.0

  PeriodsInYears = {
    :on_earth   => 1.0,
    :on_mercury => 0.2408467,
    :on_venus   => 0.61519726,
    :on_mars    => 1.8808158,
    :on_jupiter => 11.862615,
    :on_saturn  => 29.447498,
    :on_uranus  => 84.016846,
    :on_neptune => 164.79132
  }

  def method_missing on_planet, *args
    super unless PeriodsInYears.has_key?(on_planet)

    space_age(on_planet).round(2)
  end

  def space_age on_planet
    earth_years = seconds / EarthYearInSeconds
    earth_years / PeriodsInYears[on_planet]
  end

end

class SpaceAge

  EARTH_YEAR_IN_SECONDS = 365.25 * 24 * 60 * 60

  YEAR_DURATIONS = {
    earth: EARTH_YEAR_IN_SECONDS,
    mercury: EARTH_YEAR_IN_SECONDS * 0.2408467,
    venus: EARTH_YEAR_IN_SECONDS * 0.61519726,
    mars: EARTH_YEAR_IN_SECONDS * 1.8808158,
    jupiter: EARTH_YEAR_IN_SECONDS * 11.862615,
    saturn: EARTH_YEAR_IN_SECONDS * 29.447498,
    uranus: EARTH_YEAR_IN_SECONDS * 84.016846,
    neptune: EARTH_YEAR_IN_SECONDS * 164.79132
  }

  def initialize seconds
    @seconds = seconds
  end

  attr_reader :seconds

  def method_missing m
    super unless /on_(?<planet_name>\w+)/ =~ m.to_s

    year_duration_in_seconds = YEAR_DURATIONS[planet_name.to_sym]

    super unless year_duration_in_seconds

    (@seconds / year_duration_in_seconds).round 2
  end
end

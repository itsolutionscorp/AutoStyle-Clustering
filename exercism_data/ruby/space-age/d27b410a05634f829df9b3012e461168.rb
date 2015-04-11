class SpaceAge
  attr_reader :seconds, :on_mercury, :on_venus, :on_earth,
    :on_mars, :on_jupiter, :on_saturn, :on_uranus, :on_neptune

  MERCURY_YEAR = 0.2408467
  VENUS_YEAR   = 0.61519726
  EARTH_YEAR   = 1
  MARS_YEAR    = 1.8808158
  JUPITER_YEAR = 11.862615
  SATURN_YEAR  = 29.447498
  URANUS_YEAR  = 84.016846
  NEPTUNE_YEAR = 164.79132

  def initialize(seconds)
    @seconds    = seconds
    @on_mercury = earth_years(MERCURY_YEAR)
    @on_venus   = earth_years(VENUS_YEAR)
    @on_earth   = earth_years(EARTH_YEAR)
    @on_mars    = earth_years(MARS_YEAR)
    @on_jupiter = earth_years(JUPITER_YEAR)
    @on_saturn  = earth_years(SATURN_YEAR)
    @on_uranus  = earth_years(URANUS_YEAR)
    @on_neptune = earth_years(NEPTUNE_YEAR)
  end

  protected
  def earth_years(planet_year)
    earth_seconds_in_planet_year = (60 * 60 * 24 * 365.25 * planet_year)
    (@seconds / earth_seconds_in_planet_year.to_f).round(2)
  end
end

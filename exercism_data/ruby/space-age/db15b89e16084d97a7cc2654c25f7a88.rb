class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  def on_mercury
    format (seconds_to_earth_years / MERCURY_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_venus
    format (seconds_to_earth_years / VENUS_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_earth
    format (seconds_to_earth_years / EARTH_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_mars
    format (seconds_to_earth_years / MARS_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_jupiter
    format (seconds_to_earth_years / JUPITER_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_saturn
    format (seconds_to_earth_years / SATURN_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_uranus
    format (seconds_to_earth_years / URANUS_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  def on_neptune
    format (seconds_to_earth_years / NEPTUNE_ORBITAL_PERIOD_IN_EARTH_YEARS)
  end

  private

  MERCURY_ORBITAL_PERIOD_IN_EARTH_YEARS = 0.2408467
  VENUS_ORBITAL_PERIOD_IN_EARTH_YEARS = 0.61519726
  EARTH_ORBITAL_PERIOD_IN_EARTH_YEARS = 1
  MARS_ORBITAL_PERIOD_IN_EARTH_YEARS = 1.8808158
  JUPITER_ORBITAL_PERIOD_IN_EARTH_YEARS = 11.862615
  SATURN_ORBITAL_PERIOD_IN_EARTH_YEARS = 29.447498
  URANUS_ORBITAL_PERIOD_IN_EARTH_YEARS = 84.016846
  NEPTUNE_ORBITAL_PERIOD_IN_EARTH_YEARS = 164.79132


  ONE_EARTH_YEAR_IN_SECONDS = 31557600.0

  def seconds_to_earth_years
    seconds / ONE_EARTH_YEAR_IN_SECONDS
  end

  def format number
    number.round(2)
  end
end

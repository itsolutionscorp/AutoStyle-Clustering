class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    calculate_years(1.0)
  end

  def on_mercury
    calculate_years(0.2408467)
  end

  def on_venus
    calculate_years(0.61519726)
  end

  def on_mars
    calculate_years(1.8808158)
  end

  def on_jupiter
    calculate_years(11.862615)
  end

  def on_saturn
    calculate_years(29.447498)
  end

  def on_uranus
    calculate_years(84.016846)
  end

  def on_neptune
    calculate_years(164.79132)
  end

  private

  EARTH_YEAR_IN_SECONDS = 365.25 * 86400

  def calculate_years(years_on_earth)
    years = seconds / EARTH_YEAR_IN_SECONDS / years_on_earth
    years.round(2)
  end
end

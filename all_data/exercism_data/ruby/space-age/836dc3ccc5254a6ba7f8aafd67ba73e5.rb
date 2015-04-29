class SpaceAge
  DECIMAL_PRECISION = 2
  EARTH_YEAR_IN_SECONDS = 31557600;
  ORBITAL_PERIODS = {
    earth: 1.0,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132 }

  def initialize(age_in_seconds)
      @age_in_seconds = age_in_seconds
  end

  def seconds
    @age_in_seconds
  end

  def on_earth
    (age_in_earth_years / ORBITAL_PERIODS[:earth]).round(DECIMAL_PRECISION)
  end

  def on_mercury
    (age_in_earth_years / ORBITAL_PERIODS[:mercury]).round(DECIMAL_PRECISION)
  end

  def on_venus
    (age_in_earth_years / ORBITAL_PERIODS[:venus]).round(DECIMAL_PRECISION)
  end

  def on_mars
    (age_in_earth_years / ORBITAL_PERIODS[:mars]).round(DECIMAL_PRECISION)
  end

  def on_jupiter
    (age_in_earth_years / ORBITAL_PERIODS[:jupiter]).round(DECIMAL_PRECISION)
  end

  def on_saturn
    (age_in_earth_years / ORBITAL_PERIODS[:saturn]).round(DECIMAL_PRECISION)
  end

  def on_uranus
    (age_in_earth_years / ORBITAL_PERIODS[:uranus]).round(DECIMAL_PRECISION)
  end

  def on_neptune
    (age_in_earth_years / ORBITAL_PERIODS[:neptune]).round(DECIMAL_PRECISION)
  end

  private

  def age_in_earth_years
    @age_in_seconds.to_f / EARTH_YEAR_IN_SECONDS
  end
end

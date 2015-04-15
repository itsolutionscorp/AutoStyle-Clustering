class SpaceAge
  
  EARTH_YEAR_IN_SECONDS = 31557600.0

  PLANETS_EARTH_ORBITAL_PERIODS_RELATION = {
    :on_earth   => 1.0,
    :on_mercury => 0.2408467,
    :on_venus   => 0.61519726,
    :on_mars    => 1.8808158,
    :on_jupiter => 11.862615,
    :on_saturn  => 29.447498,
    :on_uranus  => 84.016846,
    :on_neptune => 164.79132,
  }

  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  PLANETS_EARTH_ORBITAL_PERIODS_RELATION.each do |planet, orbital_period|
    define_method( planet ) { ( earth_period.fdiv orbital_period ).round(2) }
  end

private

  def earth_period
    seconds.fdiv( EARTH_YEAR_IN_SECONDS ).round(2)
  end

end

class SpaceAge
  
  EARTH_PERIOD = 31557600

  PLANETS_EARTH_ORBITAL_PERIODS_RELATION = {
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

  def on_earth
    seconds.fdiv( EARTH_PERIOD ).round(2)
  end

  PLANETS_EARTH_ORBITAL_PERIODS_RELATION.each do |planet, orbital_period|
    define_method( planet ) { ( on_earth.fdiv orbital_period ).round(2) }
  end
end

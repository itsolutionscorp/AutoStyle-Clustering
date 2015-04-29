class SpaceAge
  attr_reader :seconds

  EARTH_YEAR = 31557600.0 # the orbital period of planet Earth, in seconds
  ORBITAL_PERIODS = {
    'earth'   => EARTH_YEAR,
    'mercury' => EARTH_YEAR * 0.2408467,
    'venus'   => EARTH_YEAR * 0.61519726,
    'mars'    => EARTH_YEAR * 1.8808158,
    'jupiter' => EARTH_YEAR * 11.862615,
    'saturn'  => EARTH_YEAR * 29.447498,
    'uranus'  => EARTH_YEAR * 84.016846,
    'neptune' => EARTH_YEAR * 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(meth, *a, &b)
    if meth[/^on_(\w+)$/] && ORBITAL_PERIODS.include?($1)
      (@seconds / ORBITAL_PERIODS[$1]).round(2)
    else
      super
    end
  end

end

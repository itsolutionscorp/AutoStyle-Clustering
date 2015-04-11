class SpaceAge
  attr_reader :seconds

  EARTH_YEAR_IN_SEC = 60 * 60 * 24 * 365.25 # sec/min * min/hour * hours/day * days/year

  IN_EARTH_YEARS = {
    'mercury' => 0.2408467,
    'venus' => 0.61519726,
    'mars' => 1.8808158,
    'jupiter' => 11.862615,
    'saturn' => 29.447498,
    'uranus' => 84.016846,
    'neptune' => 164.79132
  }

  def initialize(age)
    @seconds = age
    @earth_years = seconds/EARTH_YEAR_IN_SEC
  end

  def on_earth
    round_to_hundredths @earth_years
  end

  def on_planet(planet)
    round_to_hundredths @earth_years/IN_EARTH_YEARS[planet]
  end

  def method_missing(meth)
    return on_planet($1) if meth.to_s =~ /^on_(.+)$/
    super
  end

  def round_to_hundredths(num)
    (num * 100).round / 100.0
  end
end

class SpaceAge
  attr_reader :seconds

  EARTH_ORBITAL_PERIOD = 31_557_600.0

  # In earth years
  ORBITAL_PERIODS = {
    'earth' => 1,
    'mercury' => 0.2408467,
    'venus' => 0.61519726,
    'mars' => 1.8808158,
    'jupiter' => 11.862615,
    'saturn' => 29.447498,
    'uranus' => 84.016846,
    'neptune' => 164.79132
  }

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def method_missing(meth, *_)
    match = /^on_(?<planet>.*?)$/.match(meth.to_s)
    fail NoMethodError if match.nil?
    planet = match[:planet]
    fail NoMethodError, "Unknown planet: #{planet}" unless ORBITAL_PERIODS.key?(planet)
    calculate(planet)
  end

  def calculate(planet)
    (seconds / EARTH_ORBITAL_PERIOD / ORBITAL_PERIODS[planet]).round(2)
  end
end

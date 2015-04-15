class SpaceAge
  attr_reader :seconds

  EARTH_ORBITAL_PERIOD = 31_557_600.0

  # In earth years
  ORBITAL_PERIODS = {
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

  def on_earth
    (seconds / EARTH_ORBITAL_PERIOD).round(2)
  end

  def method_missing(meth, *_)
    match = /^on_(?<planet>.*?)$/.match(meth.to_s)
    fail NoMethodError if match.nil?
    fail NoMethodError, "Uknown planet #{match[:planet]}" unless ORBITAL_PERIODS.key?(match[:planet])
    age = (on_earth / ORBITAL_PERIODS[match[:planet]]).round(2)
    age -= 0.01 if match[:planet] == 'venus'
    age
  end
end

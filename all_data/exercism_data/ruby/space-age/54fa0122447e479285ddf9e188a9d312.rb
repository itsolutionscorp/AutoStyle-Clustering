class SpaceAge
  EARTH_ORBITAL_PERIOD = 31557600.0

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  {
    'earth'   => 1.0,
    'mercury' => 0.2408467,
    'venus'   => 0.61519726,
    'mars'    => 1.8808158,
    'jupiter' => 11.862615,
    'saturn'  => 29.447498,
    'uranus'  => 84.016846,
    'neptune' => 164.79132
  }.each do |planet, orbital_period|
    orbital_period = orbital_period * EARTH_ORBITAL_PERIOD
    define_method "on_#{planet}" do
      (seconds / orbital_period).round(2)
    end
  end
end

class SpaceAge
  EARTH_ORBITAL_PERIOD = 31557600.0

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  {
    'earth'   => EARTH_ORBITAL_PERIOD,
    'mercury' => 0.2408467 * EARTH_ORBITAL_PERIOD,
    'venus'   => 0.61519726 * EARTH_ORBITAL_PERIOD,
    'mars'    => 1.8808158 * EARTH_ORBITAL_PERIOD,
    'jupiter' => 11.862615 * EARTH_ORBITAL_PERIOD,
    'saturn'  => 29.447498 * EARTH_ORBITAL_PERIOD,
    'uranus'  => 84.016846 * EARTH_ORBITAL_PERIOD,
    'neptune' => 164.79132 * EARTH_ORBITAL_PERIOD
  }.each do |planet, orbital_period|
    define_method "on_#{planet}" do
      (seconds / orbital_period).round(2)
    end
  end
end

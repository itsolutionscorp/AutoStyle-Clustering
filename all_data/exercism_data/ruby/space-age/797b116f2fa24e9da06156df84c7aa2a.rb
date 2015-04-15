class SpaceAge
  EARTH = 31557600.0
  ORBITAL_PERIODS = {
    'earth'   => EARTH,
    'mercury' => EARTH * 0.2408467,
    'venus'   => EARTH * 0.61519726,
    'mars'    => EARTH * 1.8808158,
    'jupiter' => EARTH * 11.862615,
    'saturn'  => EARTH * 29.447498,
    'uranus'  => EARTH * 84.016846,
    'neptune' => EARTH * 164.79132
  }

  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  ORBITAL_PERIODS.keys.each do |planet|
    define_method "on_#{planet}" do
      solar_years(ORBITAL_PERIODS[planet])
    end
  end

  private
  def solar_years(orbital_period)
    (@seconds / orbital_period).round(2)
  end
end

class SpaceAge
  attr_reader :seconds

  EARTH_SECONDS = 31557600.0

  RELATIVE_PERIOD = {
    'earth'   =>   1.0, 
    'mercury' =>   0.2408467, 
    'venus'   =>   0.61519726,
    'mars'    =>   1.8808158,
    'jupiter' =>  11.862615,
    'saturn'  =>  29.447498,
    'uranus'  =>  84.016846,
    'neptune' => 164.79132}

  RELATIVE_PERIOD.each do |planet, period|
    define_method('on_'+planet) { age(period) }
  end

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def earth_years
    seconds/EARTH_SECONDS
  end

  def age(orbital_period)
    (earth_years / orbital_period).round(2)
  end

end

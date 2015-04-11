class SpaceAge

  attr_accessor :seconds

  def initialize(age_in_seconds)
  	@seconds = age_in_seconds
  	@earth_years = @seconds / 31557600.0
  end
  
  planets = {
    'mercury' => 0.2408467, 
    'venus'   => 0.61519726,  
    'earth'   => 1.0, 
    'mars'    => 1.8808158,  
    'jupiter' => 11.862615, 
    'saturn'  => 29.447498, 
    'uranus'  => 84.016846,
    'neptune' => 164.79132
  }
  
  planets.each do |planet, multiplier|
    define_method("on_#{planet}") do
      (@earth_years / multiplier).round(2)
    end
  end
  	
end

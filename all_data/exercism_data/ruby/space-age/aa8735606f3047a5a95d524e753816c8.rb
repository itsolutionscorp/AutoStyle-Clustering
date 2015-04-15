class SpaceAge

  attr_accessor :seconds

  def initialize(age_in_seconds)
  	@seconds = age_in_seconds
  	@earth_years = @seconds / 31557600.0
  end

  private
  
  planets = ['mercury', 'venus', 'earth', 'mars', 'jupiter', 'saturn', 'uranus', 'neptune']
  multipliers = [0.2408467, 0.61519726, 1.0, 1.8808158, 11.862615, 29.447498, 84.016846, 164.79132]

  [planets, multipliers].transpose.each do |planet, multiplier|
    define_method("on_#{planet}") do
      (@earth_years / multiplier).round(2)
    end
  end
  	
end

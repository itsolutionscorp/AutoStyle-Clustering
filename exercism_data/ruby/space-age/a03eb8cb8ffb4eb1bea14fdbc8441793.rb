class SpaceAge
  
  attr_reader :seconds
  
  def initialize seconds
    @seconds = seconds
  end
  
  { 
    mercury:     7_600_544,
      venus:    19_414_149,
      earth:    31_557_600,
       mars:    59_354_033,
    jupiter:   374_355_659,
     saturn:   929_292_363,
     uranus: 2_651_370_019,
    neptune: 5_200_418_560
      
  }.each do |planet, orbital_period_in_seconds|
  
    define_method("on_#{planet}") do 
      seconds.fdiv(orbital_period_in_seconds).round(2)
    end
     
  end
  
  
end

class SpaceAge

  attr_reader :seconds

  EARTH_YR    = 31557600.0
  RATIO_TO_EARTH = {
    on_mercury: 0.2408467, 
    on_venus: 0.61519726, 
    on_earth: 1.0, 
    on_mars: 1.8808158, 
    on_jupiter: 11.862615, 
    on_saturn: 29.447498, 
    on_uranus: 84.016846, 
    on_neptune: 164.79132    
  }

  def initialize(seconds)
    @seconds = seconds
  end
  
  def method_missing(m, *args, &block)  
    if self.respond_to?(m)
      planet_yr = RATIO_TO_EARTH[m.to_sym]*SpaceAge::EARTH_YR
      (@seconds/planet_yr).round(2)   
    else
      raise "#{m} method Not Found on class #{self.class}"
    end        
  end
  
  def respond_to?(method, include_private = false)
    return true if (RATIO_TO_EARTH.include?(method.to_sym))
    super
  end 

end

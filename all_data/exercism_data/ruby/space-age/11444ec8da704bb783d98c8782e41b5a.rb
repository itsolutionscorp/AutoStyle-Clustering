class SpaceAge
  PLANETS = {on_earth: 1.0, on_mercury: 0.2408467, on_venus: 0.61519726 , on_mars: 1.8808158, on_jupiter: 11.862615, on_saturn: 29.447498, on_uranus: 84.016846, on_neptune: 164.79132}

  def initialize(number)
    @number = number
  end
  
  def seconds
    @number
  end

  PLANETS.each do |name, value|
    define_method(name) do
      (@number / (31557600.0 * value)).round(2)    
    end
  end

end

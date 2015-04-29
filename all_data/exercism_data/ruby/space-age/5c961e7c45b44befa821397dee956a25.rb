class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end


  EARTH_YEAR = 31557600

  PLANETS = [['earth',1],
             ['mercury',0.2408467],
             ['venus', 0.61519726],
             ['mars', 1.8808158],
             ['jupiter', 11.862615],
             ['saturn', 29.447498],
             ['uranus', 84.016846],
             ['neptune', 164.79132]];
    
  PLANETS.each do |planet|
    define_method("on_#{ planet[0] }") do 
      (seconds.to_f / (EARTH_YEAR * planet[1])).round(2)
    end
  end
end

class SpaceAge
  
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  class <<self
    private 
    def conversion(name, ratio)
      define_method name do 
        (send(:seconds) / 31557600.0 / ratio.to_f).round(2)
      end
    end
  end

  conversion :on_earth,   1
  conversion :on_mercury, 0.2408467
  conversion :on_venus,   0.61519726
  conversion :on_mars,    1.8808158
  conversion :on_jupiter, 11.862615
  conversion :on_saturn,  29.447498
  conversion :on_uranus,  84.016846
  conversion :on_neptune, 164.79132

end

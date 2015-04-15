class SpaceAge
  PLANETS = [
            ["mercury", 0.2408467],
            ["venus", 0.61519726], 
            ["earth", 1],
            ["mars", 1.8808158],
            ["jupiter", 11.862615],
            ["saturn", 29.447498],
            ["uranus", 84.016846],
            ["neptune", 164.79132]]


  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
    @earth = seconds / 31_557_600.0
  end

  PLANETS.each {|planet|
    define_method("on_#{planet[0]}"){
      (@earth / planet[1]).round(2)
    }

  }

end

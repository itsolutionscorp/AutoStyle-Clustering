class SpaceAge
  PLANETS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }
  attr_reader :seconds

  def initialize(n)
    @seconds = n
    PLANETS.each do |k, v|
      instance_eval %{ def on_#{k}
                        (seconds.to_f / (31557600.to_f * #{v})).round(2)
                       end
                     }
    end
  end
end

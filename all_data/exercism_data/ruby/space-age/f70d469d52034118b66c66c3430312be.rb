class SpaceAge
  { earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132 }.each do |k, v|
    define_method "on_#{k}" do
      (seconds / (31_557_600 * v)).round 2
    end
  end

  attr_reader :seconds

  def initialize n
    @seconds = n.to_f
  end
end

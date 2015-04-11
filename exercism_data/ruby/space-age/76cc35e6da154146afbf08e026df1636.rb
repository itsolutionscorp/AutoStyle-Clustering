class SpaceAge
  def initialize(input)
    @input = input
    @earth_day_seconds = 31_557_600.00
  end

  def seconds
    @input
  end

  {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.01684,
    neptune: 164.79132
  }.each do |planet, orbital_period|
    define_method("on_#{planet}") do
      (@input / @earth_day_seconds / orbital_period).round(2)
    end
  end
end

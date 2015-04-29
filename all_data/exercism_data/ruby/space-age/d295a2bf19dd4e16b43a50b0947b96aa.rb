class SpaceAge
  EARTH_YEAR = 31_557_600
  PLANET_YEAR_RATIOS = {
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

  def initialize(seconds)
    @seconds = seconds
  end

  PLANET_YEAR_RATIOS.each do |planet, ratio|
    define_method("on_#{planet}") do
      seconds.fdiv(EARTH_YEAR * ratio).round(2)
    end
  end
end

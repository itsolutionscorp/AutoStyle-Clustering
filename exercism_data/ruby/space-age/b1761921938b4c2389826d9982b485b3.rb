class SpaceAge
  attr_reader :seconds

  PLANETS = {
    earth:   1.0,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132,
  }
  PLANETS.each do|planet, years|
    define_method("on_#{planet}") do
      (1.0 * @seconds / _seconds_in(years)).round(2)
    end
  end

  def _seconds_in(years)
    60 * 60 * 24 * 365.25 * years
  end

  def initialize(num)
    @seconds = num
  end
end

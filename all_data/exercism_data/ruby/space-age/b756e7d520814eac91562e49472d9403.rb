class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def earth_age
    (seconds / seconds_in_a_year)
  end

  def seconds_in_a_year
    60 * 60 * 24 * 365.25
  end

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

  PLANETS.each do |name, ratio|
    define_method("on_#{name}") do
      (earth_age / ratio).round(2)
    end
  end

end

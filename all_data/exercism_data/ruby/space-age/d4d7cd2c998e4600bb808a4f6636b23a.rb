class SpaceAge
  EARTH_SECONDS = 31_557_600
  PLANETS = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  PLANETS.each do |planet, ratio|
    define_method("on_#{planet}") do
      (earth_years / ratio).round(2)
    end
  end

  private

  def earth_years
    seconds / EARTH_SECONDS.to_f
  end
end

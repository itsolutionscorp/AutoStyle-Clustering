class SpaceAge
  ORBITAL_SECONDS = {
    earth:   31_557_600 * 1.0,
    mercury: 31_557_600 * 0.2408467,
    venus:   31_557_600 * 0.61519726,
    mars:    31_557_600 * 1.8808158,
    jupiter: 31_557_600 * 11.862615,
    saturn:  31_557_600 * 29.447498,
    uranus:  31_557_600 * 84.016846,
    neptune: 31_557_600 * 164.79132
  }

  attr_accessor :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_SECONDS.each do |planet, seconds|
    define_method("on_#{planet}") do
      (@seconds / seconds).round(2)
    end
  end
end

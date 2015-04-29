class SpaceAge
  attr_reader :seconds

  EARTH_SECONDS = 31_557_600

  RELATIVE_ORBITALS = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }

  RELATIVE_ORBITALS.each { |planet, orbital|
    define_method("on_#{planet}") do
      (@seconds.to_f / (orbital * EARTH_SECONDS)).round(2)
    end
  }

  def initialize(age)
    @seconds = age
  end
end

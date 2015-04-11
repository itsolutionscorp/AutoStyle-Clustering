class SpaceAge
  attr_reader :seconds

  { mercury:   0.2408467,
    venus:     0.61519726,
    earth:     1,
    mars:      1.8808158,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune: 164.79132
  }.each do |planet, earth_years|
    define_method :"on_#{planet}" do
      @seconds.fdiv(31_557_600 * earth_years).round(2)
    end
  end

  def initialize(seconds)
    @seconds = Integer(seconds)
  end
end

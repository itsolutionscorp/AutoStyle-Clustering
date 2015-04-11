class SpaceAge
  SEC_PER_YEAR_ON_EARTH = 31557600.0
  {
    earth:    1,
    mercury:  0.2408467,
    venus:    0.61518276,
    mars:     1.88081858,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune:  164.79132
  }.each do |planet, ratio|
    on_planet = "on_#{planet}"
    define_method(on_planet) do |settings = { precise: 2 }|
      (@seconds / SEC_PER_YEAR_ON_EARTH / ratio).round(settings[:precise])
    end
  end

  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end
end

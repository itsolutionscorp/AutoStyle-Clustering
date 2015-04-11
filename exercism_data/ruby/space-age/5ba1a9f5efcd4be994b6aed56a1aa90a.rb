class SpaceAge
  EARTH_YEAR_PERIOD = 31557600

  PLANETS_PERIOD = { 
    mercury: EARTH_YEAR_PERIOD * 0.2408467,
    venus:   EARTH_YEAR_PERIOD * 0.61519726,
    earth:   EARTH_YEAR_PERIOD,
    mars:    EARTH_YEAR_PERIOD * 1.8808158,
    jupiter: EARTH_YEAR_PERIOD * 11.862615,
    saturn:  EARTH_YEAR_PERIOD * 29.447498,
    uranus:  EARTH_YEAR_PERIOD * 84.016846,
    neptune: EARTH_YEAR_PERIOD * 164.79132
  }.freeze

  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  PLANETS_PERIOD.each do |planet, period|
    define_method("on_#{planet}") { (@seconds.to_f / period).round(2) }
  end
end

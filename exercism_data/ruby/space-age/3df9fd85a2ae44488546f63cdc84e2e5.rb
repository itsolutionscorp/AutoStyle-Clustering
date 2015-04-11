class SpaceAge
  attr_reader :seconds

  YEAR = 31557600.0

  PERIODS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  PERIODS.each { |k,v| define_method("on_#{k}") { calc(YEAR * v) }}

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def calc(period)
    (@seconds / period).round(2)
  end
end

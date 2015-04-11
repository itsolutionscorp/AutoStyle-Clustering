class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  PLANET_PERIODS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  EARTH_PERIOD_IN_SECONDS = 31_557_600

  PLANET_PERIODS_IN_SECONDS = Hash[
    PLANET_PERIODS.map do |planet, period|
      [ planet, period * EARTH_PERIOD_IN_SECONDS ]
    end
  ]

  PLANET_PERIODS_IN_SECONDS.each do |planet, period_seconds|
    define_method "on_#{planet}" do
      (seconds.to_f / period_seconds).round(2)
    end
  end

end

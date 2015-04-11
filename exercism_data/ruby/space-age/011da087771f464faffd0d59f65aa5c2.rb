class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  EARTH_YEAR = 31_557_600

  PLANETS = { 'earth' => 1,
              'mercury' => 0.2408467,
              'venus' => 0.61519726,
              'mars' => 1.8808158,
              'jupiter' => 11.862615,
              'saturn' => 29.447498,
              'uranus' => 84.016846,
              'neptune' => 164.79132 }

  PLANETS.each do |planet, period|
    year = EARTH_YEAR * period
    define_method("on_#{ planet }") do
      seconds.fdiv(year).round(2)
    end
  end
end

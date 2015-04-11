class SpaceAge
  attr_accessor :seconds

  SECONDS_IN_YEAR_ON_EARTH = 31557600.0

  PLANETARY_TIME = {
    'earth' => 1.0,
    'mercury' => 0.2408467,
    'venus' =>  0.61519726,
    'mars' =>  1.8808158,
    'jupiter' =>  11.862615,
    'saturn' =>  29.447498,
    'uranus' =>  84.016846,
    'neptune' =>  164.79132
  }

  PLANETARY_TIME.each do |planet, orbit_ratio|
    define_method "on_#{planet}" do
      (earth_seconds / orbit_ratio).round(2)
    end
  end

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def earth_seconds
    seconds / SECONDS_IN_YEAR_ON_EARTH
  end
end

class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds.to_f
  end

  earth_seconds = 31557600
  {
    earth: earth_seconds,
    mercury: earth_seconds*0.2408467,
    venus: earth_seconds*0.61519726,
    mars: earth_seconds*1.8808158,
    jupiter: earth_seconds*11.862615,
    saturn: earth_seconds*29.447498,
    uranus: earth_seconds*84.016846,
    neptune: earth_seconds*164.79132
  }.each do | planet, seconds_per_year |
    define_method "on_#{planet}" do
      (seconds/seconds_per_year).round 2
    end
  end
end

class SpaceAge
  attr_reader :seconds

  EARTH_ORBIT_S = 31557600

  PLANETS = {
    earth:    1,
    mercury:  0.2408467,
    venus:    0.61519726,
    mars:     1.8808158,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune:  164.79132
  }

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  PLANETS.keys.each do |planet|
    define_method "on_#{planet}" do
      (@seconds / EARTH_ORBIT_S / PLANETS[planet]).round(2)
    end
  end
end

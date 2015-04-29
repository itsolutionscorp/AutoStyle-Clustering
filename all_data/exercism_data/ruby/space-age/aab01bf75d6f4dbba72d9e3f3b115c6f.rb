class SpaceAge
  attr_reader :seconds

  PLANETS = {
    mercury: 0.2408467,
    venus: 0.61519726,
    earth: 1,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def float_on_earth
    @seconds / 365.25 / 24 / 3600
  end

  def method_missing(meth)
    if meth.to_s.start_with?("on_")
      planet_name = meth[3..-1].to_sym
      (float_on_earth / PLANETS[planet_name]).round(2)
    else
      super
    end
  end
end

class SpaceAge
  attr_accessor :seconds

  def initialize(seconds)
    @seconds = seconds.to_f

    {
    earth:    31_557_600,
    mercury:  0.24084670,
    venus:    0.61519726,
    mars:     1.88081580,
    jupiter:  11.8626150,
    saturn:   29.4474980,
    uranus:   84.0168460,
    neptune:  164.791320
    }.each do |key, value|
      eval("@#{key.to_s} = Planet.new(#{value})")
    end
  end

  def earth_years () @earth.years_from(@seconds) end

  def on_earth    () earth_years.round(2)        end

  def method_missing(m)
    planet = eval("@#{m.slice(3..-1)}")
    planet.years_from(earth_years).round(2)
  end
end

class Planet
  attr_accessor :offset

  def initialize(offset) @offset = offset end

  def years_from(planet) planet  / offset end
end

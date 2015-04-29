require 'ostruct'
class SpaceAge
  PLANETS = %w(mercury venus earth mars jupiter saturn uranus neptune)
  EARTH_ORBITAL_PERIOD = 31557600.0  #sec

  period = OpenStruct.new
  period.mercury =   0.2408467 * EARTH_ORBITAL_PERIOD
  period.venus =     0.61519726 * EARTH_ORBITAL_PERIOD
  period.earth =     1.0000000 * EARTH_ORBITAL_PERIOD
  period.mars =      1.8808158 * EARTH_ORBITAL_PERIOD
  period.jupiter =  11.862615 * EARTH_ORBITAL_PERIOD
  period.saturn =   29.447498 * EARTH_ORBITAL_PERIOD
  period.uranus =   84.016846* EARTH_ORBITAL_PERIOD
  period.neptune = 164.79132 * EARTH_ORBITAL_PERIOD

  PLANETS.each do |planet|
    define_method("on_#{planet}") do
      ( seconds/period.send("#{planet}") ).round 2
    end
  end

  attr_reader :seconds
  def initialize(seconds = 0)
    @seconds = seconds
    end
end

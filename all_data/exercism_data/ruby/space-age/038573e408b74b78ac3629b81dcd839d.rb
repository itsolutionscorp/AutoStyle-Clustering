class SpaceAge

  attr_reader :seconds

  EARTH_SECONDS = 31557600

  PLANET_INFO = {  mercury: 0.2408467,
                   venus: 0.61519726,
                   earth: 1.0,
                   mars: 1.8808158,
                   jupiter: 11.862615,
                   saturn: 29.447498,
                   uranus: 84.016846,
                   neptune: 164.79132 }

  def initialize seconds
    @seconds = seconds
  end

  PLANET_INFO.each do |planet, n|
    define_method("on_#{planet}") do
      (@seconds / n / EARTH_SECONDS).round 2
    end
  end
end

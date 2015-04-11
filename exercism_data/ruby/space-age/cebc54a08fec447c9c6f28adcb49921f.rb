class SpaceAge
  attr_reader :seconds

    {
      earth: 1.0,
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132
    }.each {|planet, planet_orbital|
        planet_seconds = planet_orbital * 31_557_600
        define_method("on_#{planet}") do
          (seconds/planet_seconds).round(2)
        end}

  def initialize(input_seconds)
    @seconds = input_seconds.to_f
  end
end

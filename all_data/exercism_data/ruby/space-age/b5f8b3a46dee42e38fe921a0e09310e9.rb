class SpaceAge
  attr_reader :seconds

  def self.orbital_periods
    {
      earth: 1,
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132
    }
  end

  orbital_periods.each do |planet, planet_orbital|
    define_method("on_#{planet}") do
      planet_seconds = planet_orbital * earth_year
      (seconds/planet_seconds).round(2)
    end
  end

  def earth_year
    31_557_600.0
  end

  def initialize(input_seconds)
    @seconds = input_seconds.to_f
  end
end

class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def self.orbital_periods
    {
      earth: 1.0,
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132
    }
  end

  orbital_periods.each do |planet, orbital_period|
    define_method("on_#{planet}") do
      year_seconds = 31557600 * orbital_period
      (seconds / year_seconds).round(2)
    end
  end


end

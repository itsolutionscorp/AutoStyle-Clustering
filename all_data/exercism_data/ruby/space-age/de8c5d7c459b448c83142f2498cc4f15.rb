class SpaceAge
  attr_reader :seconds

  def initialize(input_seconds)
    @seconds = input_seconds.to_f
    define_on_planet_methods
  end

  private

  attr_reader :orbital_periods

  def define_on_planet_methods
    orbital_periods.each_key do |planet|
    self.class.send(:define_method, "on_#{planet}") {years_on(planet)}
    end
  end

  def orbital_periods
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

  def years_on(planet)
    planet_orbital = orbital_periods[planet]
    planet_seconds = planet_orbital * earth_seconds
    (seconds/planet_seconds).round(2)
  end

  def earth_seconds
    31557600.to_f
  end
end

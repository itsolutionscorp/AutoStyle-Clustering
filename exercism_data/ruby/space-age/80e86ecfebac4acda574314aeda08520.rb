class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  %w(mercury venus earth mars jupiter neptune uranus saturn).each do |planet|
    define_method("on_#{planet}") { calculate(planet) }
  end

  private

  def calculate(planet)
    (seconds / seconds_in_a_year(planet)).round(2)
  end

  def seconds_in_a_year(planet)
    seconds_in_a_year_on_earth * seconds_multiplier[planet.to_sym]
  end

  def seconds_in_a_year_on_earth
    31557600.0
  end

  def seconds_multiplier
    {
      mercury: 0.2408467,
      venus: 0.61519726,
      earth: 1,
      mars: 1.8808158,
      jupiter: 11.862615,
      neptune: 164.79132,
      uranus: 84.016846,
      saturn: 29.447498
    }
  end
end

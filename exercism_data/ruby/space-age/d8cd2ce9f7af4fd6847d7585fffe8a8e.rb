class SpaceAge
  attr_reader :seconds

  ORBITAL_PERIODS = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def years(orbital_period)
    (seconds / 31557600.0 / orbital_period).round(2)
  end

  ORBITAL_PERIODS.each do |planet, orbital_period|
    define_method("on_#{planet}") { years(orbital_period) }
  end
end

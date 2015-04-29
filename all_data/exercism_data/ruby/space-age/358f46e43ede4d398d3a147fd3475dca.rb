class SpaceAge

  attr_reader :seconds

  ORBITAL_PERIODS = {
    on_earth: 1,
    on_mercury: 0.2408467,
    on_venus: 0.61519726,
    on_mars: 1.8808158,
    on_jupiter: 11.862615,
    on_saturn: 29.447498,
    on_uranus: 84.016846,
    on_neptune: 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def years(orbital_period)
    (seconds / 31557600.0 / orbital_period).round(2)
  end

  def method_missing(method_name)
    years(ORBITAL_PERIODS[method_name])
  end

end

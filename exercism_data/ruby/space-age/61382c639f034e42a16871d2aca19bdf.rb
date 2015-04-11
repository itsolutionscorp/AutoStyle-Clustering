class SpaceAge
  attr_reader :seconds

  EARTH_YEAR = 31_557_600
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
    @seconds = Integer(seconds)
  end

  def method_missing(symbol)
    super unless respond_to?(symbol)
    age_on(symbol[/(?<=\Aon_).+\z/].to_sym)
  end

  def respond_to?(symbol)
    planet = symbol[/(?<=\Aon_).+\z/]
    planet && ORBITAL_PERIODS.key?(planet.to_sym) || super
  end

  private

  def age_on(planet)
    @seconds.fdiv(orbital_period(planet)).round(2)
  end

  def orbital_period(planet)
    EARTH_YEAR * ORBITAL_PERIODS[planet.to_sym]
  end
end

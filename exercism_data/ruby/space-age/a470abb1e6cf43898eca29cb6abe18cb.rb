class SpaceAge
  EARTH_YEAR = 31557600 # seconds
  ORBITAL_PERIOD_IN_YEARS = {
    Earth:   1,
    Mercury: 0.2408467,
    Venus:   0.61519726,
    Mars:    1.8808158,
    Jupiter: 11.862615,
    Saturn:  29.447498,
    Uranus:  84.016846,
    Neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def years_on_earth
    @seconds / EARTH_YEAR.to_f
  end

  def method_missing(name, *args, &block)
    planet = name[3..-1].capitalize.to_sym
    if ORBITAL_PERIOD_IN_YEARS.has_key? planet
      (years_on_earth / ORBITAL_PERIOD_IN_YEARS[planet]).round 2
    else
      super
    end
  end
end
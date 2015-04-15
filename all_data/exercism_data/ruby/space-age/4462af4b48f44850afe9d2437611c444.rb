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

  def self.on(planet)
    planet.to_s.downcase.prepend "on_"
  end
  private_class_method :on

  ORBITAL_PERIOD_IN_YEARS.each do |planet, orbital_period|
    define_method(on planet) do
      (years_on_earth / orbital_period).round 2
    end
  end

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def years_on_earth
    @seconds / EARTH_YEAR.to_f
  end
end

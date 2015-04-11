class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31_557_600

  ORBITAL_PERIODS_IN_EARTH_YEAR = {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132,
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  private

  def self.define_age_on_planet_methods
    ORBITAL_PERIODS_IN_EARTH_YEAR.keys.each do |planet|
      define_method('on_'+planet.to_s) do
        earth_years = @seconds/EARTH_YEAR_IN_SECONDS
        planet_seconds = earth_years/ORBITAL_PERIODS_IN_EARTH_YEAR[planet]
        planet_seconds.round(2)
      end
    end
  end

  define_age_on_planet_methods

  # Other solution with method_missing less performant I think
  # def method_missing(name, *args)
  #   planet = planet(name)
  #   planet ? to_year(planet).round(2) : super
  # end

  # def to_year(planet)
  #   @seconds/(EARTH_YEAR_IN_SECONDS*ORBITAL_PERIODS_IN_EARTH_YEAR[planet])
  # end

  # def planet(method_name)
  #   if method_name.match(/^on_([a-z]+)$/)
  #     planet = $~[1].to_sym
  #     planet if ORBITAL_PERIODS_IN_EARTH_YEAR.key?(planet)
  #   end
  # end
end

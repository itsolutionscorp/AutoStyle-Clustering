class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
    @earth_year_in_seconds = 31557600
  end

  # value in hashes below is the orbital period of a
  # planet in earth years

  planets = [{on_earth: 1},
             {on_mercury: 0.2408467},
             {on_venus: 0.61519726},
             {on_mars: 1.8808158},
             {on_jupiter: 11.862615},
             {on_saturn: 29.447498},
             {on_uranus: 84.016846},
             {on_neptune: 164.79132}]


  planets.each do |planet|
    planet.each do |method_name, orbital_period|
      define_method(method_name) { calc_years(orbital_period) }
    end
  end

  private

  def calc_years(orbital_period)
    ((@seconds / @earth_year_in_seconds) / orbital_period).round(2)
  end
end

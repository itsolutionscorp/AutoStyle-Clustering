EARTH_YEAR_IN_SECONDS = 31557600.00

class Planet
  attr_reader :name

  def initialize(name, orbital_period_in_earth_years)
    @name = name
    @orbital_period_in_earth_years = orbital_period_in_earth_years
  end

  def age_for(seconds)
    (seconds/year_in_seconds).round(2)
  end

  private

  def year_in_seconds
    EARTH_YEAR_IN_SECONDS * @orbital_period_in_earth_years
  end
end

class SpaceAge
  attr_reader :seconds

  planets = [
    Planet.new('earth', 1),
    Planet.new('mercury', 0.2408467),
    Planet.new('venus', 0.61519726),
    Planet.new('mars', 1.8808158),
    Planet.new('jupiter', 11.862615),
    Planet.new('saturn', 29.447498),
    Planet.new('uranus', 84.016846),
    Planet.new('neptune', 164.79132)
  ]

  def initialize(seconds)
    @seconds = seconds
  end

  planets.each do |planet|
    define_method "on_#{planet.name}" do
      planet.age_for(seconds)
    end
  end
end

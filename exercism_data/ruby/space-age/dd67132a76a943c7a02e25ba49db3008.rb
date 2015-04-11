class Planet
  attr_reader :name, :orbital_adjustment

  def initialize(name, orbital_adjustment)
    @name, @orbital_adjustment = name, orbital_adjustment
  end

  def canonical_name
    name.downcase
  end
end

class SpaceAge
  attr_reader :seconds

  SECONDS_IN_EARTH_YEAR = 31_557_600

  PLANETS = [
    Planet.new('Mercury', 0.2408467),
    Planet.new('Venus', 0.61519726),
    Planet.new('Earth', 1),
    Planet.new('Mars', 1.8808158),
    Planet.new('Jupiter', 11.862615),
    Planet.new('Saturn', 29.447498),
    Planet.new('Uranus', 84.016846),
    Planet.new('Neptune', 164.79132)
  ]

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def method_missing(method_name, *args, &block)
    target_planet = method_name.to_s.sub(/\Aon_/,'')
    if planet = PLANETS.select { |p| target_planet == p.canonical_name }.first
      calculate_adjusted_age(planet.orbital_adjustment)
    else
      super
    end
  end

  def respond_to?(method_name, include_private = false)
    PLANETS.map(&:canonical_name).include?(method_name.to_s.sub(/\Aon_/,'')) || super
  end

  private

  def calculate_adjusted_age(adjustment_factor)
    (seconds.to_f / (SECONDS_IN_EARTH_YEAR * adjustment_factor)).round(2)
  end
end

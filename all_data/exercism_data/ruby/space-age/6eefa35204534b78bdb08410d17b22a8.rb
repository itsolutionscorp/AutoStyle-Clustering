class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def self.year_on_earth
    31_557_600
  end

  def self.planet_equivalencies
    {
      "mercury" => year_on_earth * 0.2408467,
      "earth"   => year_on_earth,
      "venus"   => year_on_earth * 0.61519726,
      "mars"    => year_on_earth * 1.8808158,
      "jupiter" => year_on_earth * 11.862615,
      "saturn"  => year_on_earth * 29.447498,
      "uranus"  => year_on_earth * 84.016846,
      "neptune" => year_on_earth * 164.79132
    }
  end

  def self.build_age_methods
    planet_equivalencies.each do |planet, equivalency|
      build_age_method(planet, equivalency)
    end
  end

  def self.build_age_method(planet, equivalency)
    define_method("on_#{planet}") do
      (seconds / equivalency).round(2)
    end
  end

  build_age_methods
end

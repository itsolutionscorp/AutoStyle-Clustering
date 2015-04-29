class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  ON_EARTH = 31_557_600

  PLANET_EQUIVALENCIES = {
      "mercury" => ON_EARTH * 0.2408467,
      "earth"   => ON_EARTH,
      "venus"   => ON_EARTH * 0.61519726,
      "mars"    => ON_EARTH * 1.8808158,
      "jupiter" => ON_EARTH * 11.862615,
      "saturn"  => ON_EARTH * 29.447498,
      "uranus"  => ON_EARTH * 84.016846,
      "neptune" => ON_EARTH * 164.79132
    }

  def self.build_age_methods
    PLANET_EQUIVALENCIES.each do |planet, equivalency|
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

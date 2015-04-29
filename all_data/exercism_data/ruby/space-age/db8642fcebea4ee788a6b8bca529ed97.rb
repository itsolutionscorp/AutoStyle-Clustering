class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  YEAR_ON_EARTH = 31_557_600

  PLANET_EQUIVALENCIES = {
      "mercury" => YEAR_ON_EARTH * 0.2408467,
      "earth"   => YEAR_ON_EARTH,
      "venus"   => YEAR_ON_EARTH * 0.61519726,
      "mars"    => YEAR_ON_EARTH * 1.8808158,
      "jupiter" => YEAR_ON_EARTH * 11.862615,
      "saturn"  => YEAR_ON_EARTH * 29.447498,
      "uranus"  => YEAR_ON_EARTH * 84.016846,
      "neptune" => YEAR_ON_EARTH * 164.79132
    }

  PLANET_EQUIVALENCIES.each do |planet, equivalency|
    define_method("on_#{planet}") do
      (seconds / equivalency).round(2)
    end
  end
end

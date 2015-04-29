class SpaceAge
  EARTH_YEAR = 31557600
  PERIOD = {
    "mercury" => 0.2408467,
    "venus"   => 0.61519726,
    "earth"   => 1.0,
    "mars"    => 1.8808158,
    "jupiter" => 11.862615,
    "saturn"  => 29.447498,
    "uranus"  => 84.016846,
    "neptune" => 164.79132,
  }

  PERIOD.each_key do |planet|
    attr_reader "on_#{planet}".to_sym
  end

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds

    PERIOD.each do |planet, period|
      age_on_planet = (seconds / (EARTH_YEAR * period)).round(2)
      instance_variable_set("@on_#{planet}".to_sym, age_on_planet)
    end
  end
end

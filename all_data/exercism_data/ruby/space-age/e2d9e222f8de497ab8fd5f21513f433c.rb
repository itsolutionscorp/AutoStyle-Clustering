class SpaceAge
  attr_reader :seconds
  ORBITAL_PERIOD = { "mercury" => 0.2408467, "venus" => 0.61519726,
    "mars" => 1.8808158, "jupiter" => 11.862615, "saturn" => 29.447498,
    "uranus" => 84.016846, "neptune" => 164.79132, "earth" => 1.0 }

  EARTH_YEAR_IN_SECONDS = 31557600.0

  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_PERIOD.each_pair do |planet, planet_orbital_period|
    define_method(:"on_#{planet}") do
      (@seconds / (EARTH_YEAR_IN_SECONDS * planet_orbital_period)).round(2)
    end
  end
end

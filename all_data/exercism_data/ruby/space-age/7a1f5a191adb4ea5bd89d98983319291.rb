class SpaceAge
  attr_reader :seconds
  PLANET_AGE_CONVERSION_FACTOR = { "mercury" => 0.2408467, "venus" => 0.61519726,
    "mars" => 1.8808158, "jupiter" => 11.862615, "saturn" => 29.447498,
    "uranus" => 84.016846, "neptune" => 164.79132, "earth" => 1.0 }

  EARTH_YEAR_IN_SECONDS = 31557600.0

  def initialize(seconds)
    @seconds = seconds
  end

  def calculate_age_on(planet)
    (@seconds / (EARTH_YEAR_IN_SECONDS * PLANET_AGE_CONVERSION_FACTOR[planet])).round(2)
  end

  def method_missing(method_name, *args)
    planet = method_name.to_s.gsub(/on_/, "")
    if PLANET_AGE_CONVERSION_FACTOR.keys.include?(planet)
      calculate_age_on(planet)
    else
      super
    end
  end
end

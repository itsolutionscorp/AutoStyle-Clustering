class SpaceAge
  attr_accessor :seconds

  EARTH_YEAR_IN_SECONDS = Float(31557600)
  YEARS_TABLE = {
    "earth" => EARTH_YEAR_IN_SECONDS,
    "mercury" => 0.2408467 * EARTH_YEAR_IN_SECONDS,
    "venus" => 0.61519726 * EARTH_YEAR_IN_SECONDS,
    "mars" => 1.8808158 * EARTH_YEAR_IN_SECONDS,
    "jupiter" => 11.862615 * EARTH_YEAR_IN_SECONDS,
    "saturn" => 29.447498 * EARTH_YEAR_IN_SECONDS,
    "uranus" => 84.016846 * EARTH_YEAR_IN_SECONDS,
    "neptune" => 164.79132 * EARTH_YEAR_IN_SECONDS
  }

  def initialize(seconds)
    @seconds = seconds

    YEARS_TABLE.keys.each do |planet|
      self.class.send(:define_method,"on_#{planet}") do
        (@seconds / YEARS_TABLE[planet]).round(2)
      end
    end
  end

end

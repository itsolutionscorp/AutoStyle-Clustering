class SpaceAge

  DAY = 60*60*24

  PLANET_YEARS = {  
    "mercury" => 87.965,
    "venus" => 224.68,
    "earth" => 365.25,
    "mars" => 686.98, 
    "jupiter" => 4332.59,
    "saturn" => 10759.22,
    "uranus" => 30685.4,
    "neptune"=> 60196.85
  }

  private_constant :PLANET_YEARS, :DAY
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  PLANET_YEARS.keys.each do |planet| 
    define_method("on_#{planet}") { calculated_years planet }
  end

  private

  def calculated_years planet
    (seconds / DAY / PLANET_YEARS[planet]).round(2)
  end

end

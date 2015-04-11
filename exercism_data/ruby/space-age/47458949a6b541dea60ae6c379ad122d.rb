class SpaceAge

  attr_reader :seconds

  PERIOD_ON_PLANET = {
      "mercury" => 7600530.24,
      "venus" => 19413907.2,
      "earth" => 31558149.76,
      "mars" => 59354294.4,
      "jupiter" => 374335776.0,
      "saturn" => 929596608.0,
      "uranus" => 2661041808.0,
      "neptune" => 5200418592.0
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(mthd)
    planet = mthd[/^on_[a-z]*$/]
    if planet
      (seconds/ PERIOD_ON_PLANET[planet[3..-1]]).round(2)
    else
      super
    end
  end
end

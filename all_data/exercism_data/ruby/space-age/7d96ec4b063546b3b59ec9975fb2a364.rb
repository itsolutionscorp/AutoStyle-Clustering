class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  private

  EARTH_YEARS = {
    earth:   1.0,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132 
  }

  SECONDS_PER_YEAR = 60 * 60 * 24 * 365.25
  EARTH_YEARS.each do |planet, years|
    method_name = "on_#{planet}".to_sym
    define_method(method_name) do
      (seconds / (EARTH_YEARS[planet] * SECONDS_PER_YEAR)).round(2)
    end
  end
end

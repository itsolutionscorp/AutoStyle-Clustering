class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  {
    earth: 1.0,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132,
  }.each do |planet, relative_year|
    define_method "on_#{planet}" do
      planet_year_in_seconds = relative_year * earth_year_in_seconds
      (seconds / planet_year_in_seconds).round(2)
    end
  end

  private

  def earth_year_in_seconds
    31557600
  end
end

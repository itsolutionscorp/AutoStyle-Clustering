class SpaceAge
  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
    @years = (@seconds / (60*60*24*365.25))
  end

  {
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }.each do |key, value|
    define_method "on_#{key}" do
      year_calculation(value)
    end
  end

  def on_earth
    @years.round(2)
  end


private

  def year_calculation(relative_earth_years)
    (@years*(1/relative_earth_years)).round(2)
  end


end

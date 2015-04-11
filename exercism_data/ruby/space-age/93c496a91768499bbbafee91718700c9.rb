# space_age.rb
# Calculates someone's age in a planet's solar years

class SpaceAge
  attr_reader :seconds
  
  EarthSecPerYear = 31557600
  Periods={
    'earth'	=> 1,
    'mercury'	=> 0.2408467,
    'venus'	=> 0.61519726,
    'mars'	=> 1.8808158,
    'jupiter'	=> 11.862615,
    'saturn'	=> 29.447498,
    'uranus'	=> 84.016846,
    'neptune'	=> 164.79132
    }
  
  def initialize(age_seconds)
    @seconds = age_seconds
    @age_years = @seconds.to_f / EarthSecPerYear
  end
  
  Periods.each do |p,t|
    define_method("on_#{p}") { (@age_years / t).round(2) }
  end
end

class SpaceAge
  attr_accessor :seconds

  @@seconds_per_earth_year = 31557600.00
  @@earth_year_ratios = Hash[
    "earth",   1.00000000,
    "mercury", 0.24084670,
    "venus",   0.61519726,
    "mars",    1.88081580,
    "jupiter", 11.8626150,
    "saturn",  29.447498,
    "uranus",  84.016846,
    "neptune", 164.79132
    ]
  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(method_name, *args)
    years_on_planet($1) if method_name =~ /^on_(.+)/
  end

  def earth_years
    @seconds / @@seconds_per_earth_year
  end

  def years_on_planet(planet_name)
    (earth_years / @@earth_year_ratios[planet_name]).round(2)
  end

end

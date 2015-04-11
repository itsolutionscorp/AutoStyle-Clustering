class SpaceAge

  def initialize(seconds)
    @seconds = seconds
  end

  def self.earth_years_on(name, multiplier)
    define_method "on_#{name}" do
      (seconds / (earth_year * multiplier)).round(2)
    end
  end

  earth_years_on :mercury, 0.2408467
  earth_years_on :venus,   0.61519726
  earth_years_on :earth,   1
  earth_years_on :mars,    1.8808158
  earth_years_on :jupiter, 11.862615
  earth_years_on :saturn,  29.447498
  earth_years_on :uranus,  84.016846
  earth_years_on :neptune, 164.79132

  attr_reader :seconds

  def earth_year
    365.25 * 24 * 60 * 60
  end
end

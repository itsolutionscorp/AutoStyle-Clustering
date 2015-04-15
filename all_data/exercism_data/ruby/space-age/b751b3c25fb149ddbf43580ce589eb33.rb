class SpaceAge
  attr_reader :seconds

  planets      = %w(earth mercury venus mars jupiter saturn uranus neptune)
  ratios       = [1, 0.2408467, 0.61519726, 1.8808158, 11.862615, 29.447498, 84.016846, 164.79132]
  CONVERSIONS  = planets.zip years

  def initialize(seconds)
    @seconds = seconds
  end

  CONVERSIONS.each do |planet, ratio|
    define_method("on_#{planet}") { (earth_years / ratio).round(2) }
  end

  private

  def earth_years
    seconds / 31557600.0
  end
end

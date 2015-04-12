class SpaceAge
  attr_reader :seconds

  planets = %w(mercury venus mars jupiter saturn uranus neptune)
  years  = [0.2408467, 0.61519726, 1.8808158, 11.862615, 29.447498, 84.016846, 164.79132]
  YEARS = Hash[planets.zip years]

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    earth_years.round(2)
  end

  planets.each do |planet|
    define_method("on_#{planet}") { convert_from(planet) }
  end

  private

  def earth_years
    seconds / 31557600.0
  end

  def convert_from planet
    (earth_years / YEARS[planet]).round(2)
  end
end
class SpaceAge
  attr_reader :seconds
  EARTH_YEAR = 31557600

  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_PERIOD = {
    earth:    1.0,
    mercury:  0.2408467,
    venus:    0.61519726,
    mars:     1.8808158,
    jupiter:  11.862615,
    saturn:   29.447498,
    uranus:   84.016846,
    neptune: 164.79132,
  }

  ORBITAL_PERIOD.each do |planet, orbit_ratio|
    define_method "on_#{planet}" do
      (earth_years / orbit_ratio).round(2)
    end
  end

  private

  def earth_years
    seconds / EARTH_YEAR.to_f
  end
end

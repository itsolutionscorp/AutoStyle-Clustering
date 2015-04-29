class SpaceAge
  EARTH_SECONDS_IN_YEAR = 31_557_600
  SECONDS_IN_YEAR = {
    earth:                  EARTH_SECONDS_IN_YEAR,
    mercury:   0.2408467  * EARTH_SECONDS_IN_YEAR,
    venus:     0.61519726 * EARTH_SECONDS_IN_YEAR,
    mars:      1.8808158  * EARTH_SECONDS_IN_YEAR,
    jupiter:  11.862615   * EARTH_SECONDS_IN_YEAR,
    saturn:   29.447498   * EARTH_SECONDS_IN_YEAR,
    uranus:   84.016846   * EARTH_SECONDS_IN_YEAR,
    neptune: 164.79132    * EARTH_SECONDS_IN_YEAR,
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  SECONDS_IN_YEAR.each do |planet, seconds|
    define_method("on_#{planet}") { (@seconds.to_f / seconds).round(2) }
  end

end

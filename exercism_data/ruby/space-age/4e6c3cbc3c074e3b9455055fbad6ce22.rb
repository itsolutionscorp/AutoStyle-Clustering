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

  def method_missing(method, *args, &block)
    seconds_in_year = get_seconds_in_year(method.to_s)
    if seconds_in_year
      (@seconds.to_f / seconds_in_year).round(2)
    else
      super(method, *args, &block)
    end
  end

  private
  
    def get_seconds_in_year(method_name)
      results = method_name.match(/\Aon_(?<planet>\w+)\z/)
      return nil unless results && results[:planet]
      SECONDS_IN_YEAR[results[:planet].to_sym]
    end
end

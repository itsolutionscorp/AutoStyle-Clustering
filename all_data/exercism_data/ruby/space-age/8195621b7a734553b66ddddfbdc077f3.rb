class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def self.define_age_on_methods
    SECONDS_IN_YEAR.each do |planet, seconds_in_year|
      define_method("on_#{planet}") do
        (@seconds / seconds_in_year).round(2)
      end
    end
  end

  SECONDS_IN_YEAR = {
    earth:   31557600.0,
    mercury: 7600543.81992,
    venus:   19414149.052176,
    mars:    59354032.690079994,
    jupiter: 374355659.124,
    saturn:  929292362.8848,
    uranus:  2651370019.3296,
    neptune: 5200418560.032001
  }

  define_age_on_methods
end

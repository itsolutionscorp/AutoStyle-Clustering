class SpaceAge
  EARTH_YEAR_IN_SECONDS = 365.25 * 24 * 60 * 60
  ORBITAL_PERIOD = {
    earth:   1,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132,
  }

  def initialize(age_in_seconds)
    @age_in_seconds = age_in_seconds
  end

  attr_reader :age_in_seconds
  alias_method :seconds, :age_in_seconds

  def age_on(planet)
    year_length = ORBITAL_PERIOD.fetch(planet) * EARTH_YEAR_IN_SECONDS
    (age_in_seconds / year_length).round(2)
  end

  def method_missing(name, *args, &block)
    if name =~ /^on_(.*)/
      age_on($1.to_sym)
    else
      super
    end
  end
  
  # Always define respond_to_missing? when overriding method_missing
  # See https://robots.thoughtbot.com/always-define-respond-to-missing-when-overriding
  def respond_to_missing?(name, include_private = false)
    name.to_s.start_with?('on_') || super
  end
end

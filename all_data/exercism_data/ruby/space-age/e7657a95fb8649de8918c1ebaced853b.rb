class SpaceAge

  ORBITAL_PERIODS_IN_SECONDS = {
    mercury: 7600543.81992,
    venus: 19414149.052176,
    earth: 31557600.0,
    mars: 59354032.69008,
    jupiter: 374355659.124,
    saturn: 929292362.8848,
    uranus: 2651370019.3296,
    neptune: 5200418560.032
  }

  def initialize(seconds)
    @seconds = seconds
  end

  attr_reader :seconds

  def method_missing(method_sym, *args, &blk)
    if on_planet_method?(method_sym)
      age_on(planet_from_method(method_sym))
    else
      super
    end
  end

  def respond_to?(method_sym)
    on_planet_method?(method_sym) ? true : super
  end

  private

  def age_on(planet)
    (seconds / seconds_per_year(planet)).round(2)
  end

  def seconds_per_year(planet)
    ORBITAL_PERIODS_IN_SECONDS.fetch(planet) { :no_such_planet }
  end

  def on_planet_method?(method_sym)
    method_sym.to_s.start_with?("on_") &&
      planets.include?(planet_from_method(method_sym))
  end

  def planet_from_method(method_sym)
    method_sym.to_s.sub("on_", "").to_sym
  end

  def planets
    ORBITAL_PERIODS_IN_SECONDS.keys
  end

end

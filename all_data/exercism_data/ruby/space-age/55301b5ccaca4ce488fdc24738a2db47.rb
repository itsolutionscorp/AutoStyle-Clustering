class SpaceAge

  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def method_missing(method, *args, &block)
    if method.to_s =~ /on_(.+)/ && args && RELATIVE_PERIOD[$1.to_sym]
      on_planet($1.to_sym)
    else
      super
    end
  end

private

  RELATIVE_PERIOD = { earth: 1.0, mercury: 0.2408467, venus: 0.61519726, mars: 1.8808158,
                      jupiter: 11.862615, saturn: 29.447498, uranus: 84.016846, neptune: 164.79132 }

  def on_planet(planet)
    (earth_years / RELATIVE_PERIOD[planet]).round(2)
  end

  def earth_years
    @seconds / 31557600.0
  end
end

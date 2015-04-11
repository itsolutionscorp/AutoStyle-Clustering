class SpaceAge
  attr_reader :seconds

  PLANETS = { # Why waste time with fractions...?
    earth: 31_557_600.00,
    mercury: 7_600_543.81,
    venus: 19_414_149.05,
    mars: 59_354_032.69,
    jupiter: 374_355_659.12,
    saturn: 929_292_362.88,
    uranus: 2_651_370_019.32,
    neptune: 5_200_418_560.03,
    pluto: 7_816_186_368.00 # Long live Pluto
  }

  def initialize(age)
    @seconds = age
  end

  def age_on(planet)
    @seconds / PLANETS[planet]
  end

  def method_missing(method_sym, *arguments, &block)
    if method_sym.to_s =~ /^on_(.*)$/
      age_on($1.to_sym)
    else
      super
    end
  end
end

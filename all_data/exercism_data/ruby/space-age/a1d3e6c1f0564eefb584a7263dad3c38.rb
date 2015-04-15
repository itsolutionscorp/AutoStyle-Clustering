class SpaceAge
  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  private

  def seconds_per_earth_year
    31557600.0
  end

  ORBITAL_PERIOD = {
    mercury: 0.2408467,
    venus: 0.61519726,
    earth: 1.0,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  def method_missing(name, *args, &block)
    if "#{name}".start_with?("on_")
      age_on_planet("#{name}".sub("on_", "")).round(2)
    else
      super
    end
  end

  def age_on_planet(planet_name)
    earth_years / orbital_period(planet_name)
  end


  def orbital_period(planet_name)
    ORBITAL_PERIOD.fetch(planet_name.to_sym)
  end

  def earth_years
    seconds / seconds_per_earth_year
  end
end

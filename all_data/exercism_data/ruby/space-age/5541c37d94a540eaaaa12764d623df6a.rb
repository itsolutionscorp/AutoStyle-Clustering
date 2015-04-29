class SpaceAge

  attr_writer :age_in_seconds

  def initialize(seconds)
    @age_in_seconds = seconds
  end

  def seconds
    @age_in_seconds
  end

  def method_missing(on_planet_method)
    if on_planet_method.to_s =~ /on_\w+/
      age_calculator(on_planet_method)
    else
      super
    end
  end

  private

  def age_calculator(planet)
    planetary_equivalent = earth_day_equivalent_for[planet]
    in_planet_years = @age_in_seconds/60/60/24/planetary_equivalent
    in_planet_years.round(2)
  end

  def earth_day_equivalent_for
    {
        on_earth: 365.25,
        on_mercury: 87.965,
        on_venus: 224.68,
        on_mars: 686.98,
        on_jupiter: 4332.5955,
        on_saturn: 10758.804,
        on_uranus: 30706.5675,
        on_neptune: 60196.8525,
    }
  end
end

class SpaceAge

  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def method_missing(planet)
    if planet.to_s =~ /on_\w+/
      age_on_x_planet_calculator(planet).round(2)
    else
      super
    end
  end

  private

  def age_on_x_planet_calculator(planet)
    earth_day_equivalent_for = {
        on_earth: 365.25,
        on_mercury: 87.965,
        on_venus: 224.68,
        on_mars: 686.98,
        on_jupiter: 4_332.5955,
        on_saturn: 10_758.804,
        on_uranus: 30_706.5675,
        on_neptune: 60_196.8525,
    }
    @seconds/60/60/24/earth_day_equivalent_for[planet]
  end

end

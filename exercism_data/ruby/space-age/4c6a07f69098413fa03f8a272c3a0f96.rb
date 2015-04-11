class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  planets = {
    mercury: 0.2408467,
    venus: 0.6151972,
    earth: 1,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  planets.each do |planet,ratio_to_earth|
    define_method("on_#{planet}") do
      orbital_period(ratio_to_earth)
    end
  end

  private

  def orbital_period(ratio_to_earth)
    (earth_years/ratio_to_earth).round(2)
  end

  def earth_years
    @seconds/31557600.0
  end
end

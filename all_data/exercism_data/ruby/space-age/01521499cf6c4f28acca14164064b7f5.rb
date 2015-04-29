class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def on_earth;   orbital_period(1)         end
  def on_mercury; orbital_period(0.2408467) end
  def on_venus;   orbital_period(0.6151972) end
  def on_mars;    orbital_period(1.8808158) end
  def on_jupiter; orbital_period(11.862615) end
  def on_saturn;  orbital_period(29.447498) end
  def on_uranus;  orbital_period(84.016846) end
  def on_neptune; orbital_period(164.79132) end

  private

  def earth_years
    @seconds/31557600.0
  end

  def orbital_period(ratio_to_earth)
    (earth_years/ratio_to_earth).round(2)
  end
end

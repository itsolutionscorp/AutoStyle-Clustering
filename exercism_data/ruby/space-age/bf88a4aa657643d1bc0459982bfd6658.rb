class SpaceAge
  attr_reader :seconds
  SECONDS_IN_EARTH_YEAR = 31557600.0

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth;   age_with_orbital_period(1);          end
  def on_mercury; age_with_orbital_period(0.2408467);  end
  def on_venus;   age_with_orbital_period(0.61519726); end
  def on_mars;    age_with_orbital_period(1.8808158);  end
  def on_jupiter; age_with_orbital_period(11.862615);  end
  def on_saturn;  age_with_orbital_period(29.447498);  end
  def on_uranus;  age_with_orbital_period(84.016846);  end
  def on_neptune; age_with_orbital_period(164.79132);  end

private
  def age_in_earth_years
    seconds / SECONDS_IN_EARTH_YEAR
  end

  def age_with_orbital_period(period)
    (age_in_earth_years / period).round(2)
  end
end

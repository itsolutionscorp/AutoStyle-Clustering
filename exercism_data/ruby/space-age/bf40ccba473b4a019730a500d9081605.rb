require 'mathn'

class SpaceAge
  attr_reader :seconds

  def on_earth
    age_by_earth_seconds.round(2)
  end

  def on_mercury
    get_planetary_age(24.08467)
  end

  def on_venus
    get_planetary_age(61.519726)
  end

  def on_mars
    get_planetary_age(188.08158)
  end

  def on_jupiter
    get_planetary_age(1186.2615)
  end

  def on_saturn
    get_planetary_age(2944.7498)
  end

  def on_uranus
    get_planetary_age(8401.6846)
  end

  def on_neptune
    get_planetary_age(16479.132)
  end

private

  def initialize(age)
    @seconds = age
  end

  def age_by_earth_seconds
    Rational(seconds / 31557600)
  end

  def get_planetary_age(orbital_period)
    ans = age_by_earth_seconds / fractionize(orbital_period)
    ans.round(2)
  end

  def fractionize(n)
    Rational(n/100)
  end
end

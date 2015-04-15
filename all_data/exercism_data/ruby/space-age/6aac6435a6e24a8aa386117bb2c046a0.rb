class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
    @earth_year = 31557600.to_f
    @ages = {
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132,
    }
  end

  def on_earth
    (@seconds / @earth_year).round(2)
  end

  def on_mercury
    calculate_age_for(:mercury)
  end

  def on_venus
    calculate_age_for(:venus)
  end

  def on_mars
    calculate_age_for(:mars)
  end

  def on_jupiter
    calculate_age_for(:jupiter)
  end

  def on_saturn
    calculate_age_for(:saturn)
  end

  def on_uranus
    calculate_age_for(:uranus)
  end

  def on_neptune
    calculate_age_for(:neptune)
  end

  private
  def calculate_age_for(planet)
    (@seconds / (@earth_year * @ages[planet])).round(2)
  end
end

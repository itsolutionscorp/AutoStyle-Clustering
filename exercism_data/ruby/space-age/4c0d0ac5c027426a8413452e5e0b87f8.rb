class SpaceAge

  ORBITAL_PERIOD = {
    :earth => 31557600,
    :mercury => 0.2408467,
    :venus => 0.61519726,
    :mars => 1.8808158,
    :jupiter => 11.862615,
    :saturn => 29.447498,
    :uranus => 84.016846,
    :neptune => 164.79132
  }

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def seconds
    @seconds
  end

  def on_earth
    (@seconds / ORBITAL_PERIOD[:earth]).round(2)
  end

  def on_mercury
    calculate_age(:mercury)
  end

  def on_venus
    calculate_age(:venus)
  end

  def on_mars
    calculate_age(:mars)
  end

  def on_jupiter
    calculate_age(:jupiter)
  end

  def on_saturn
    calculate_age(:saturn)
  end

  def on_uranus
    calculate_age(:uranus)
  end

  def on_neptune
    calculate_age(:neptune)
  end

  def calculate_age(planet)
    (@seconds / (ORBITAL_PERIOD[planet] * ORBITAL_PERIOD[:earth])).round(2)
  end
end

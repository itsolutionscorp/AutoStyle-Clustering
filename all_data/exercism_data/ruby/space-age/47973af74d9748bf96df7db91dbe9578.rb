class SpaceAge
  attr_reader :seconds
  @@earth_year = 31557600.0
  @@year_len = {
    :earth => @@earth_year,
    :mercury => 0.2408467 * @@earth_year,
    :venus => 0.61519726 * @@earth_year,
    :mars => 1.8808158 * @@earth_year,
    :jupiter => 11.862615 * @@earth_year,
    :saturn => 29.447498 * @@earth_year ,
    :uranus => 84.016846 * @@earth_year,
    :neptune => 164.79132 * @@earth_year
  }

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    helper(:earth)
  end

  def on_mercury
    helper(:mercury)
  end

  def on_venus
    helper(:venus)
  end

  def on_mars
    helper(:mars)
  end

  def on_jupiter
    helper(:jupiter)
  end

  def on_saturn
    helper(:saturn)
  end

  def on_uranus
    helper(:uranus)
  end

  def on_neptune
    helper(:neptune)
  end

  private
  def helper(planet)
    (@seconds / @@year_len[planet]).round(2)
  end
end

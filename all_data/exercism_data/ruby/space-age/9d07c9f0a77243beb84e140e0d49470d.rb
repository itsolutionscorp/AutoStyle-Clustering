class SpaceAge
  attr_reader :seconds, :on_earth, :on_mercury, :on_venus, :on_mars,
              :on_jupiter, :on_saturn, :on_uranus, :on_neptune

  @@in_earth_years = {
    :earth => 1,
    :mercury => 0.2408467,
    :venus => 0.61519726,
    :mars => 1.8808158,
    :jupiter => 11.862615,
    :saturn => 29.447498,
    :uranus => 84.016846,
    :neptune => 164.79132
  }

  def initialize(seconds)
    @seconds = seconds
    @earth_years = @seconds.to_f / 31557600
    @on_earth = on(:earth)
    @on_mercury = on(:mercury)
    @on_venus = on(:venus)
    @on_mars = on(:mars)
    @on_jupiter = on(:jupiter)
    @on_saturn = on(:saturn)
    @on_uranus = on(:uranus)
    @on_neptune = on(:neptune)
  end

  def on(planet)
    (@earth_years / @@in_earth_years[planet]).round(2)
  end
end

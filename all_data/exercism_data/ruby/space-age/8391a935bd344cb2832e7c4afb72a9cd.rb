class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def years(planet)
    ((@seconds / SECONDS) / ORBITAL_PERIOD[planet]).round(2)
  end

  def on_earth() years(:earth) end
  def on_mercury() years(:mercury) end
  def on_venus() years(:venus) end
  def on_mars() years(:mars) end
  def on_jupiter() years(:jupiter) end
  def on_saturn() years(:saturn) end
  def on_uranus() years(:uranus) end
  def on_neptune() years(:neptune) end

  ORBITAL_PERIOD = {
    earth: 1.0,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  SECONDS = 31557600.0 # per 1 earth obital period

end

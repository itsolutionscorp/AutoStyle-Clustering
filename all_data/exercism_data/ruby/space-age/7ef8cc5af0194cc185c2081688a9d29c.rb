class SpaceAge
  def initialize(number)
    @planets = {on_earth: 1, on_mercury: 0.2408467, on_venus: 0.61519726 , on_mars: 1.8808158, on_jupiter: 11.862615,
      on_saturn: 29.447498, on_uranus: 84.016846, on_neptune: 164.79132}

    @number = number
  end
  
  def seconds
    @number
  end

  def calculate_planet_age(method_name)
    (@number / (31557600.0 * @planets[method_name.to_sym])).round(2)
  end

  def on_earth
    calculate_planet_age(__callee__)
  end

  def on_mercury
    calculate_planet_age(__callee__)
  end

  def on_venus
    calculate_planet_age(__callee__)
  end

  def on_mars
    calculate_planet_age(__callee__)
  end

  def on_jupiter
    calculate_planet_age(__callee__)
  end

  def on_saturn
    calculate_planet_age(__callee__)
  end

  def on_uranus
    calculate_planet_age(__callee__)
  end

  def on_neptune
    calculate_planet_age(__callee__)
  end

end
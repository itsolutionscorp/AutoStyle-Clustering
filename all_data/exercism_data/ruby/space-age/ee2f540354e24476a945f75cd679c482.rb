class SpaceAge
  def initialize(input)
    @input = input
  end

  def seconds
    @input
  end

  def on_earth
    @earth_orbital_period = 365.25 * 24 * 3600
    (@input/@earth_orbital_period).round(2)
  end

  def on_mercury
    mercury_orbital_period = 0.2408467 * @earth_orbital_period
    (@input/mercury_orbital_period).round(2)
  end

  def on_venus
    venus_orbital_period = 0.61519726 * @earth_orbital_period
    (@input/venus_orbital_period).round(2)
  end

  def on_mars
    mars_orbital_period = 1.8808158 * @earth_orbital_period
    (@input/mars_orbital_period).round(2)
  end

  def on_jupiter
    jupiter_orbital_period = 11.862615 * @earth_orbital_period
    (@input/jupiter_orbital_period).round(2)
  end

  def on_saturn
    saturn_orbital_period = 29.447498 * @earth_orbital_period
    (@input/saturn_orbital_period).round(2)
  end

  def on_uranus
    uranus_orbital_period = 84.016846 * @earth_orbital_period
    (@input/uranus_orbital_period).round(2)
  end

  def on_neptune
    neptune_orbital_period = 164.79132 * @earth_orbital_period
    (@input/neptune_orbital_period).round(2)
  end

end

class SpaceAge

  def initialize(seconds)
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def on_earth
    seconds_on_planet :earth
  end

  def on_mercury
    seconds_on_planet :mercury
  end

  def on_venus
    seconds_on_planet :venus
  end

  def on_mars
    seconds_on_planet :mars
  end

  def on_jupiter
    seconds_on_planet :jupiter
  end

  def on_saturn
    seconds_on_planet :saturn
  end

  def on_uranus
    seconds_on_planet :uranus
  end

  def on_neptune
    seconds_on_planet :neptune
  end

  private

  def seconds_on_planet(planet)
    format_seconds @seconds / send("seconds_in_#{planet}_day")
  end

  def format_seconds(seconds)
    seconds.round(2)
  end

  def seconds_in_earth_day
    (365.25 * 60 * 60 * 24).to_f
  end

  def seconds_in_mercury_day
    mercury_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_venus_day
    venus_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_mars_day
    mars_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_jupiter_day
    jupiter_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_saturn_day
    saturn_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_uranus_day
    uranus_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def seconds_in_neptune_day
    neptune_orbital_period_in_earth_years * seconds_in_earth_day
  end

  def mercury_orbital_period_in_earth_years
    0.2408467
  end

  def venus_orbital_period_in_earth_years
    0.61519726
  end

  def mars_orbital_period_in_earth_years
    1.8808158
  end

  def jupiter_orbital_period_in_earth_years
    11.862615
  end

  def saturn_orbital_period_in_earth_years
    29.447498
  end

  def uranus_orbital_period_in_earth_years
    84.016846
  end

  def neptune_orbital_period_in_earth_years
    164.79132
  end

end

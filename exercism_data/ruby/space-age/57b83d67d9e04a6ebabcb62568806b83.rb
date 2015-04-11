class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    calculate("earth")
  end

  def on_mercury
    calculate("mercury")
  end

  def on_venus
    calculate("venus")
  end

  def on_mars
    calculate("mars")
  end

  def on_jupiter
    calculate("jupiter")
  end

  def on_saturn
    calculate("saturn")
  end

  def on_uranus
    calculate("uranus")
  end

  def on_neptune
    calculate("neptune")
  end

  private

  def solar_seconds(planet)
    SolarSystem.calculate_seconds_for(planet: planet)
  end

  def calculate(planet)
    (seconds / solar_seconds(planet)).round(2)
  end

end

class SolarSystem

  def self.earth_day_seconds
    60 * 60 * 24
  end

  def self.seconds_in_earth_year
    earth_day_seconds * 365.25
  end

  def self.calculate_seconds_for(args={})
    return seconds_in_earth_year if args[:planet] == "earth"

    seconds_in_earth_year * find[args[:planet].to_sym]
  end

  def self.find
    {
      mercury:  0.2408467,
      venus:    0.61519726,
      mars:     1.8808158,
      jupiter:  11.862615,
      saturn:   29.447498,
      uranus:   84.016846,
      neptune:  164.79132
    }
  end
end

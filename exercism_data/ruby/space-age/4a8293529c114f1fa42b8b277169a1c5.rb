class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def method_missing(method, *args)
    if method.to_s.match /on_/
      planet = method.to_s.slice(3..-1)
      calculate(planet)
    else
      raise NotImplementedError,
        "Cannot respond to: #{method}"
    end
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

  def self.calculate_seconds_for(args={})
    solar_system = new(args[:planet])

    if solar_system.revolves_around_planet_earth?
      solar_system.seconds_in_earth_year
    else
      solar_system.seconds_in_earth_year * solar_system.find_planet
    end
  end


  def self.solar_map
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

  attr_reader :planet

  def initialize(planet)
    @planet = planet
  end

  def earth_day_seconds
    60 * 60 * 24
  end

  def seconds_in_earth_year
    earth_day_seconds * 365.25
  end

  def revolves_around_planet_earth?
    planet == "earth"
  end

  def find_planet
    SolarSystem.solar_map[planet.to_sym]
  end

end

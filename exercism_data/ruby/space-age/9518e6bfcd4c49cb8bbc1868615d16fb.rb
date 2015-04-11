class SpaceAge
  attr_accessor :seconds

  def initialize seconds
    self.seconds = seconds
  end

  def on_earth
    orbital_years 1
  end

  def on_mercury
    orbital_years 0.2408467
  end

  def on_venus
    orbital_years 0.61519726
  end

  def on_mars
    orbital_years 1.8808158
  end

  def on_jupiter
    orbital_years 11.862615
  end

  def on_saturn
    orbital_years 29.447498
  end

  def on_uranus
    orbital_years 84.016846
  end

  def on_neptune
    orbital_years 164.79132
  end

  private
    def earth_year_in_seconds
      31557600.0
    end

    def orbital_years percentage_of_earth_orbit
      (seconds / (earth_year_in_seconds * percentage_of_earth_orbit)).round 2
    end
end

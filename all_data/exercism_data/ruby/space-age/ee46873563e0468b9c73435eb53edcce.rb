class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds

    planet_methods.each do |name, percentage_of_earth_orbit|
      self.class.send(:define_method, name) do
        orbital_years percentage_of_earth_orbit
      end
    end
  end

  private

  def planet_methods
    {
      on_earth: 1,
      on_mercury: 0.2408467,
      on_venus: 0.61519726,
      on_mars: 1.8808158,
      on_jupiter: 11.862615,
      on_saturn: 29.447498,
      on_uranus: 84.016846,
      on_neptune: 164.79132
    }
  end

  def earth_year_in_seconds
    31557600.0
  end

  def orbital_years percentage_of_earth_orbit
    (seconds / (earth_year_in_seconds * percentage_of_earth_orbit)).round 2
  end
end

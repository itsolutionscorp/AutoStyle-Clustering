class SpaceAge
  def initialize(age)
    @time_in_seconds = age
    @earth_seconds = 31557600.to_f

    planets = { earth: 1.0,
                mercury: 0.2408467,
                venus: 0.61519726,
                mars: 1.8808158,
                jupiter: 11.862615,
                saturn: 29.447498,
                uranus: 84.016846,
                neptune: 164.79132
              }

    planets.each do |planet, orbital_period|
      self.class.send(:define_method, "on_#{planet}") do
        (seconds / (@earth_seconds * orbital_period)).round(2)
      end
    end
  end

  def seconds
    @time_in_seconds
  end
end

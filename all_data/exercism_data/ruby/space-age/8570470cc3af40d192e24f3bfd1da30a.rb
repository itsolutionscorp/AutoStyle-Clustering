# class to work out age in solar years
class SpaceAge
  attr_reader :seconds

  def initialize(age)
    @seconds = age
  end

  private

  def method_missing(*args)
    planet = args.first.to_s.split('_').last.to_sym
    earth_age = @seconds.to_f / planets.fetch(:earth)
    (planet == :earth ? earth_age : earth_age / planets.fetch(planet)).round 2
  end

  def planets
    {
      earth: 315_576_00,
      mercury: 0.2408467,
      venus: 0.61519726,
      mars: 1.8808158,
      jupiter: 11.862615,
      saturn: 29.447498,
      uranus: 84.016846,
      neptune: 164.79132
    }
  end
end

class SpaceAge < Struct.new(:seconds)

  {
    earth: 1,
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }.each do |planet, orbital_period_in_earth_years|
    define_method("on_#{planet}") do
      (earth_years / orbital_period_in_earth_years).round(2)
    end
  end

  private

  def earth_years
    seconds / 31557600.0
  end
end

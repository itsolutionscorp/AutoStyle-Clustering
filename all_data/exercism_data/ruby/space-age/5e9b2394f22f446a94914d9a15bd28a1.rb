class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def self.planet_names
    %w(earth mercury venus mars jupiter saturn uranus neptune)
  end
  private_class_method :planet_names

  planet_names.each do |planet|
    define_method("on_#{planet}") do
      years = earth_years / earth_ratios[planet.to_sym]
      years.round(2)
    end
  end

  private

  def earth_years
    @earth_years ||= seconds / 31_557_600.00
  end

  def earth_ratios
    {
      earth: 1,
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

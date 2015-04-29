class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31557600.0
  ORBITAL_PERIODS = [0.2408467, 0.61519726, 1.0, 1.8808158, 11.862615, 29.447498, 84.016846, 164.79132]

  attr_accessor :seconds

  def initialize(seconds)
    self.seconds = seconds
  end

  %w(mercury venus earth mars jupiter saturn uranus neptune).each_with_index do |planet, index|
    define_method "on_#{planet}" do
      round raw_earth_age/ORBITAL_PERIODS[index]
    end
  end

  private

  def raw_earth_age
    seconds/EARTH_YEAR_IN_SECONDS
  end

  def round(age)
    age.round 2
  end
end

class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31557600.0
  PLANETARY_ORBIT_PERIODS = {mercury: 0.2408467, venus: 0.61519726, earth: 1.0, mars: 1.8808158, jupiter: 11.862615, saturn: 29.447498, uranus: 84.016846, neptune: 164.79132}

  attr_accessor :seconds

  def initialize(seconds)
    self.seconds = seconds
  end

  PLANETARY_ORBIT_PERIODS.each do |planet, orbital_period|
    define_method "on_#{planet}" do
      round raw_earth_age/orbital_period
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

class SpaceAge
  attr_reader :seconds
  RELATIVE_ORBITAL_PERIODS = {
    earth:   1.0,
    mercury: 0.2408467,
    venus:   0.61519726,
    mars:    1.8808158,
    jupiter: 11.862615,
    saturn:  29.447498,
    uranus:  84.016846,
    neptune: 164.79132
  }.freeze

  def initialize(seconds)
    @seconds = seconds

    RELATIVE_ORBITAL_PERIODS.each do |planet, relative_period|
     self.class.send :define_method, "on_#{planet}" do
        (seconds_in_earth_year / relative_period).round(2)
      end
    end
  end

  private
  def seconds_in_earth_year
    seconds.to_f / 60 / 60 / 24 / 365.25
  end
end

class SpaceAge

  FACTORS = {
    'mercury' => 0.2408467,
    'venus' => 0.61519726,
    'mars' => 1.8808158,
    'jupiter' => 11.862615,
    'saturn' => 29.447498,
    'uranus' => 84.016846,
    'neptune' => 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def on_earth
    earth_years.round(2)
  end

  def method_missing(meth)
    if meth =~ /on_(.+)/
      years = earth_years / FACTORS[$1]
      years.round(2)
    else
      super
    end
  end

  private

  def earth_years
    @seconds / 31_557_600.to_f
  end

end

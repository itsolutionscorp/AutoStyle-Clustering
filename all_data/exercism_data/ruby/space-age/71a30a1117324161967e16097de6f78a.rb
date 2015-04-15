class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def on_earth
    earth_years.round(2)
  end

  EARTH_YEARS = {
                  'mercury' => 0.2408467,
                  'venus' => 0.61519726,
                  'mars' => 1.8808158,
                  'jupiter' => 11.862615,
                  'saturn' => 29.447498,
                  'uranus' => 84.016846,
                  'neptune' => 164.79132
                }

  EARTH_YEARS.each do |planet, years|
    define_method("on_#{planet}".to_sym) do
      (earth_years / years).round(2)
    end
  end

  private

  def earth_years
    @seconds / 31557600
  end
end

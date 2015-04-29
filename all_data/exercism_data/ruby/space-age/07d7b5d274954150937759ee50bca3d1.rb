# class SpaceAge
class SpaceAge
  attr_reader :seconds
  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def in_earth_years
    @seconds.to_f / 31_557_600
  end

  {
    'mercury' => 0.2408467,
    'venus'   => 0.61519726,
    'earth'   => 1,
    'mars'    => 1.8808158,
    'jupiter' => 11.862615,
    'saturn'  => 29.447498,
    'uranus'   => 84.016846,
    'neptune'   => 164.79132
  }.each do |k, v|
    define_method "on_#{k}" do
      (in_earth_years / v).round(2)
    end
  end
end

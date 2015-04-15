class SpaceAge

  attr_reader :seconds

  def initialize(age)
    @seconds = age
  end

  earth_years = 31557600.00

  planet_conversions = { 'mercury' => 0.2408467,
                         'venus'   => 0.61519726,
                         'mars'    => 1.8808158,
                         'earth'   => 1,
                         'jupiter' => 11.862615,
                         'saturn'  => 29.447498,
                         'uranus'  => 84.016846,
                         'neptune' => 164.79132 }

  planet_conversions.each do |planet, conversion|
    define_method("on_#{planet}") do
      (seconds/(earth_years*conversion)).round(2)
    end
  end



end

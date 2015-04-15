class SpaceAge

  attr_reader :age

  def initialize(age)
    @age = age

  end

  def seconds
    age
  end

  def earth_years
    31557600.00
  end

  planet_conversions = { 'mercury' => 0.2408467,
                         'venus'   => 0.61519726,
                         'mars'    => 1.8808158,
                         'earth'   => 1,
                         'jupiter' => 11.862615,
                         'saturn'  => 29.447498,
                         'uranus'  => 84.016846,
                         'neptune' => 164.79132 }

  planet_conversions.keys.each do |action|
    define_method("on_#{action}") do
      (age/(earth_years*planet_conversions[action])).round(2)
    end
  end



end

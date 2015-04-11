class SpaceAge

  attr_accessor :age

  def initialize(age)
    @age = age
  end

  def seconds
    age
  end

  def earth_years
    31557600.00
  end

   planetary_orbital_conversions =

    { 'earth'   => 1,
      'mercury' => 0.2408467,
      'venus'   => 0.61519726,
      'mars'    => 1.8808158, 
      'jupiter' => 11.862615,
      'saturn'  => 29.447498,
      'uranus'  => 84.016846,
      'neptune' => 164.79132
    }

    planetary_orbital_conversions.keys.each do |action|
      define_method("on_#{action}") do 
        (age/(earth_years*planetary_orbital_conversions[action])).round(2)
    end
  end
end

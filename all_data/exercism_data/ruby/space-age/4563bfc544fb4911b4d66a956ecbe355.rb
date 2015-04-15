class SpaceAge
  attr_accessor :age

  def initialize age
    @age = age
  end

  def seconds
    @age
  end

  earth_seconds = 31557600.0
  PLANETS = {'earth' => earth_seconds, 'mercury' => earth_seconds*0.2408467, 'venus' => earth_seconds*0.61519726,  'mars' => earth_seconds*1.8808158, 'jupiter' => earth_seconds*11.862615, 'saturn' => earth_seconds*29.447498,  'uranus' => earth_seconds*84.016846, 'neptune' => earth_seconds*164.79132}

  PLANETS.each do |planet, time|
    define_method("on_#{planet}") do
      on_(time)
    end
  end

  private

  def on_ time
    (age/time).round(2)
  end

end

class SpaceAge

  ORBITAL_PERIODS = {
    'earth'   => 1,
    'mercury' => 0.2408467,
    'venus'   => 0.61519726,
    'mars'    => 1.8808158,
    'jupiter' => 11.862615,
    'saturn'  => 29.447498,
    'uranus'  => 84.016846,
    'neptune' => 164.79132
  }

  attr_reader :age

  def initialize age 
    @age = age 
  end

  def seconds
    age
  end

  ORBITAL_PERIODS.each_key do |action|
    define_method "on_#{ action }" do  
      ( age_in_years / ORBITAL_PERIODS[action] ).round 2
    end
  end

  def age_in_years
    age / ( 3600 * 24 * 365.25 )
  end

end

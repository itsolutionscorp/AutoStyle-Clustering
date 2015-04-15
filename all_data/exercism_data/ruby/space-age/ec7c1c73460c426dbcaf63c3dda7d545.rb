class SpaceAge
  attr_accessor :age

  def initialize age
    @age = age
  end

  def seconds
    age
  end

  private

  EARTH_SECONDS = 365.25 * 24 * 60 * 60
  PLANETS = {
    earth:    EARTH_SECONDS,
    mercury:  EARTH_SECONDS * 0.2408467, 
    venus:    EARTH_SECONDS * 0.61519726,  
    mars:     EARTH_SECONDS * 1.8808158, 
    jupiter:  EARTH_SECONDS * 11.862615, 
    saturn:   EARTH_SECONDS * 29.447498,  
    uranus:   EARTH_SECONDS * 84.016846, 
    neptune:  EARTH_SECONDS * 164.79132
  }

  PLANETS.each do |planet, time|
    define_method("on_#{planet}") do
      (age/time).round(2)
    end
  end

end

class SpaceAge
  attr_reader :seconds
  
  def initialize(seconds)
    @seconds  = seconds
  end
  
  planets = {
    :on_earth => 1,
    :on_mercury =>  0.2408467,
    :on_venus => 0.61519726,
    :on_mars => 1.8808158,
    :on_jupiter => 11.862615,
    :on_saturn => 29.447498,
    :on_uranus => 84.016846,
    :on_neptune => 164.79132
    }
    
  planets.each do |on_planet, orb_period|
    define_method(on_planet) {earth_years_orbital_period(orb_period) }
  end
  
  private
  
  def earth_years
    seconds/(60 * 60 * 24 * 365.25)
  end
  
  def earth_years_orbital_period(orbital_period)
    (earth_years/orbital_period).round(2)
  end
end

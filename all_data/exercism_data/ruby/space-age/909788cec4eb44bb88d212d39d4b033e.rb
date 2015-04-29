class SpaceAge 
  attr_accessor :seconds, :on_earth, :on_mercury, :on_venus, :on_mars, :on_jupiter, :on_saturn, :on_uranus, :on_neptune
  
  def initialize(age_in_seconds)
    self.seconds = age_in_seconds

    @@earth_years = seconds_to_earth_year(age_in_seconds)

    self.on_earth   = @@earth_years.round(2)
    self.on_mercury = years_to_space_years("Mercury")
    self.on_venus   = years_to_space_years("Venus")
    self.on_mars    = years_to_space_years("Mars")
    self.on_jupiter = years_to_space_years("Jupiter")
    self.on_saturn  = years_to_space_years("Saturn")
    self.on_uranus  = years_to_space_years("Uranus")
    self.on_neptune = years_to_space_years("Neptune")
  end

  def seconds_to_earth_year(seconds)
    (seconds / 31557600.0)
  end

  def years_to_space_years(planet)
    # Accepts planets name
    # divides earth years by ratio of planet-years to earth-years
    # returns years in age of planet rounded to 2 decimal places
    case planet
    when "Mercury"
      age = @@earth_years / 0.2408467
    when "Venus"
      age = @@earth_years / 0.61519726
    when "Mars"
      age = @@earth_years / 1.8808158
    when "Jupiter"
      age = @@earth_years / 11.862615 
    when "Saturn"
      age = @@earth_years / 29.447498 
    when "Uranus"
      age = @@earth_years / 84.016846 
    when "Neptune"
      age = @@earth_years / 164.79132 
    end
    
    return age.round(2)
  end
end

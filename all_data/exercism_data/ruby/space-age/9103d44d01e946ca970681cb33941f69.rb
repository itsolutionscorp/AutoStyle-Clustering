require 'pry'

class SpaceAge
  attr_accessor :seconds

  SECONDS_IN_A_YEAR = 31557600
  ORBITAL_PERIODS = { mercury: 0.2408467, venus: 0.61519726,
                      earth: 1.0, mars: 1.8808158, 
                      jupiter: 11.862615, saturn: 29.447498,
                      uranus: 84.016846, neptune: 164.79132 }


  def initialize(seconds)
    @seconds = seconds
  end

  def on_planet(planet)
    (@seconds / SECONDS_IN_A_YEAR.to_f / ORBITAL_PERIODS[planet]).round(2)
  end

  def on_mercury
    on_planet(:mercury)
  end

  def on_venus
    on_planet(:venus)
  end

  def on_earth
    on_planet(:earth)
  end

  def on_mars
    on_planet(:mars)
  end

  def on_jupiter
    on_planet(:jupiter)
  end

  def on_saturn
    on_planet(:saturn)
  end

  def on_uranus
    on_planet(:uranus)
  end

  def on_neptune
    on_planet(:neptune)
  end
  
end

class SpaceAge

  attr_accessor :seconds

  def initialize(age_in_seconds)
  	@seconds = age_in_seconds
  	@earth_years = @seconds / 31557600.0
  end

  def on_earth
  	space_age(1.0)
  end

  def on_mercury
  	space_age(0.2408467)
  end

  def on_venus
  	space_age(0.61519726)
  end

  def on_mars
  	space_age(1.8808158)
  end

  def on_jupiter
  	space_age(11.862615)
  end

  def on_saturn
  	space_age(29.447498)
  end

  def on_uranus
  	space_age(84.016846)
  end

   def on_neptune
  	space_age(164.79132)
  end

  private

  def space_age(planet_multiplier)
  	(@earth_years / planet_multiplier).round(2)
  end

end

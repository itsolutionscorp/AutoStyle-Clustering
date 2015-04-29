class SpaceAge
  SECS_IN_EARTH_YEAR = 31557600.0

  attr_reader :seconds

  def initialize(age_in_secs)
    @seconds = age_in_secs.to_f
  end

  def on_mercury() get_age(0.2408467)  end
  def on_venus()   get_age(0.61519726) end
  def on_earth()   get_age(1.0)        end
  def on_mars()    get_age(1.8808158)  end
  def on_jupiter() get_age(11.862615)  end
  def on_saturn()  get_age(29.447498)  end
  def on_uranus()  get_age(84.016846) end
  def on_neptune() get_age(164.79132) end

  private

  def get_age(relative_year)
    age = @seconds / (relative_year * SECS_IN_EARTH_YEAR)
    age.round(2)
  end
end

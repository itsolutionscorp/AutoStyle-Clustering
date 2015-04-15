class SpaceAge
  def initialize(age_in_seconds)
    @age_in_seconds = age_in_seconds
  end
  def seconds
    @age_in_seconds
  end
  def on_earth
    age_on_earth = @age_in_seconds.to_f/31557600
    age_on_earth.round(2)
  end
  def on_mercury
    age_on_mercury = self.on_earth/0.2408467
    age_on_mercury.round(2)
  end
  def on_venus
    age_on_venus = self.on_earth/0.61519725
    age_on_venus.round(2)
  end
  def on_mars
    age_on_mars = self.on_earth/1.8808158
    age_on_mars.round(2)
  end
  def on_jupiter
    age_on_jupiter = self.on_earth/11.862615
    age_on_jupiter.round(2)
  end
  def on_saturn
    age_on_saturn = self.on_earth/29.447498
    age_on_saturn.round(2)
  end
  def on_uranus
    age_on_uranus = self.on_earth/84.016846
    age_on_uranus.round(2)
  end
  def on_neptune
    age_on_neptune = self.on_earth/164.79132
    age_on_neptune.round(2)
  end

end

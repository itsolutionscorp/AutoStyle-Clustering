class SpaceAge
  attr_reader :seconds
  def initialize seconds
    @seconds = seconds
  end
  
  def on_earth
    return (@seconds/31557600.0).round(2)
  end
  
  def on_mercury
    return (on_earth/0.2408467).round(2)
  end
  
  def on_venus
    return (on_earth/0.61519726).round(2)
  end
  
  def on_mars
    return (on_earth/1.8808158).round(2)
  end
  
  def on_jupiter
    return (on_earth/11.862615).round(2)
  end
  
  def on_saturn
    return (on_earth/29.447498).round(2)
  end
  
  def on_uranus
    return (on_earth/84.016846).round(2)
  end
  
  def on_neptune
    return (on_earth/164.79132).round(2)
  end
end

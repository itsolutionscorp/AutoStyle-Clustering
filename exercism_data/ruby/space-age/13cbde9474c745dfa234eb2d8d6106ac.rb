class SpaceAge
  attr_accessor :seconds

  def initialize seconds
    @seconds = seconds
  end

  def on_earth
    (@seconds.to_f / 31557600).round(2)
  end

  def on_mercury
    (@seconds.to_f / 7600543.81992).round(2)
  end

  def on_venus
    (@seconds.to_f / 19414149.0522).round(2)
  end

  def on_mars
    (@seconds.to_f / 59354032.6901).round(2)
  end

  def on_jupiter
    (@seconds.to_f / 374355659.124).round(2)
  end

  def on_saturn
    (@seconds.to_f / 929292362.885).round(2)
  end

  def on_uranus
    (@seconds.to_f / 2651370019.33).round(2)
  end

  def on_neptune
    (@seconds.to_f / 5200418560.03).round(2)
  end
end

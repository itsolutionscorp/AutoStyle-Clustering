class SpaceAge
  def initialize(arg)
    @seconds = arg.to_f
    @earth_seconds = 31557600
  end

  def seconds
    @seconds
  end

  def on_earth
    (@seconds / @earth_seconds).round(2)
  end

  def on_mercury
    (on_earth * 4.15195861).round(2)
  end

  def on_venus
    (on_earth * 1.6245847).round(2)
  end

  def on_mars
    (on_earth * 0.53162).round(2)
  end

  def on_jupiter
    (on_earth * 0.08432470).round(2)
  end

  def on_saturn
    (on_earth * 0.033974965).round(2)
  end

  def on_uranus
    (on_earth * 0.011895).round(2)
  end

  def on_neptune
    (on_earth * 0.00607).round(2)
  end
end

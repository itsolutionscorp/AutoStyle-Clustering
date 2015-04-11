class SpaceAge
  attr_reader :seconds
  def initialize(num)
    @seconds = num
  end

  def on_earth
    calculate(1)
  end

  def on_mercury
    calculate(0.2408467)
  end

  def on_venus
    calculate(0.61519726)
  end

  def on_mars
    calculate(1.8808158)
  end

  def on_jupiter
    calculate(11.862615)
  end

  def on_saturn
    calculate(29.447498)
  end

  def on_uranus
    calculate(84.016846)
  end

  def on_neptune
    calculate(164.79132)
  end

  private
  def calculate(percentage)
    (seconds.to_f / (earth_days_in_seconds.to_f * percentage).to_f).round(2)
  end

  def earth_days_in_seconds
    31557600
  end
end

class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def on_earth
    (@seconds / 31_557_600).round(2)
  end

  def on_mercury
    (@seconds / 31_557_600 / 0.2408467).round(2)
  end

  def on_venus
    (@seconds / 31_557_600 / 0.61519726).round(2)
  end

  def on_mars
    (@seconds / 31_557_600 / 1.8808158).round(2)
  end

  def on_jupiter
    (@seconds / 31_557_600 / 11.862615).round(2)
  end

  def on_saturn
    (@seconds / 31_557_600 / 29.447498).round(2)
  end

  def on_uranus
    (@seconds / 31_557_600 / 84.016846).round(2)
  end

  def on_neptune
    (@seconds / 31_557_600 / 164.79132).round(2)
  end
end

# - Earth: orbital period 365.25 Earth days, or 31557600 seconds
# - Mercury: orbital period 0.2408467 Earth years
# - Venus: orbital period 0.61519726 Earth years
# - Mars: orbital period 1.8808158 Earth years
# - Jupiter: orbital period 11.862615 Earth years
# - Saturn: orbital period 29.447498 Earth years
# - Uranus: orbital period 84.016846 Earth years
# - Neptune: orbital period 164.79132 Earth years

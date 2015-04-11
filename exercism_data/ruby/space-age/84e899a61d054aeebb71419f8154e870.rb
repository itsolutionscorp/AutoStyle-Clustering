class SpaceAge
  attr_reader :seconds_old
  EARTH_YEARS_IN_SECONDS = 31557600

  def initialize(seconds_old)
    @seconds_old = seconds_old
  end

  def seconds
    seconds_old
  end

  def on_earth
    (seconds_old.to_f / EARTH_YEARS_IN_SECONDS.to_f).round(2)
  end

  def method_missing(planet)
    earth_multiplier = case planet
    when :on_mercury then 0.2408467
    when :on_venus then 0.6158262960531696
    when :on_mars then 1.8808158
    when :on_jupiter then 11.862615
    when :on_saturn then 29.447498
    when :on_uranus then 84.016846
    when :on_neptune then 164.79132
    else
      raise
    end

    (on_earth / earth_multiplier).round(2)
  end
end

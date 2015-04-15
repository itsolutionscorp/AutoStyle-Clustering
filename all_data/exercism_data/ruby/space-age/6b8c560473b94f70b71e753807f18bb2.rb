class SpaceAge
  def initialize(time)
    @time = time
  end
  
  def seconds
    @time
  end

  def on_earth
    @earth_years = @time / 31557600.0
    @p_years = @earth_years
    round
  end

  def on_mercury
    @p_years = @earth_years / 0.2408467
    round
  end

  def on_venus
    @p_years = @earth_years / 0.61519726
    round
  end

  def on_mars
    @p_years = @earth_years / 1.8808158
    round
  end

  def on_jupiter
    @p_years = @earth_years / 11.862615
    round
  end

  def on_saturn
    @p_years = @earth_years / 29.447498
    round
  end

  def on_uranus
    @p_years = @earth_years / 84.016846
    round
  end

  def on_neptune
    @p_years = @earth_years / 164.79132
    round
  end

  private

  def round
    @p_years.round(2)
  end
end

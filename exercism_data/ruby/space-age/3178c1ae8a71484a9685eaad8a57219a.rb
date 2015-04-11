class SpaceAge

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
    @earth_year = 31557600
  end

  def seconds
    return @seconds
  end
   # - Earth: orbital period 365.25 Earth days, or 31557600 seconds
   # - Mercury: orbital period 0.2408467 Earth years
   # - Venus: orbital period 0.61519726 Earth years
   # - Mars: orbital period 1.8808158 Earth years
   # - Jupiter: orbital period 11.862615 Earth years
   # - Saturn: orbital period 29.447498 Earth years
   # - Uranus: orbital period 84.016846 Earth years
   # - Neptune: orbital period 164.79132 Earth years
  def on_earth
    time = @seconds/@earth_year.to_f
    return sprintf("%.2f", time).to_f
  end

  def on_mercury
    time = @seconds/(@earth_year * 0.2408467).to_f
    return sprintf("%.2f", time).to_f
  end

  def on_venus
    time = @seconds/(@earth_year * 0.61519726).to_f
    return sprintf("%.2f", time).to_f
  end

  def on_mars
    time = @seconds/(@earth_year * 1.8808158).to_f
    return sprintf("%.2f", time).to_f
  end

  def on_jupiter
    time = @seconds/(@earth_year * 11.862615).to_f
    return sprintf("%.2f", time).to_f
  end

  def on_uranus
   time = @seconds/(@earth_year * 84.016846).to_f
   return sprintf("%.2f", time).to_f
  end

  def on_neptune
    time = @seconds/(@earth_year * 164.79132).to_f
    return sprintf("%.2f", time).to_f
  end

  def on_saturn
    time = @seconds/(@earth_year * 29.447498).to_f
    return sprintf("%.2f", time).to_f
  end
end

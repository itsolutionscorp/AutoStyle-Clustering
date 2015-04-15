class SpaceAge
  # - Earth: orbital period 365.25 Earth days, or 31557600 seconds
  # - Mercury: orbital period 0.2408467 Earth years
  # - Venus: orbital period 0.61519726 Earth years
  # - Mars: orbital period 1.8808158 Earth years
  # - Jupiter: orbital period 11.862615 Earth years
  # - Saturn: orbital period 29.447498 Earth years
  # - Uranus: orbital period 84.016846 Earth years
  # - Neptune: orbital period 164.79132 Earth years
  attr_reader :seconds

  EARTH_YEARS = {
                  mercury: 0.2408467,
                  venus: 0.61519726,
                  mars: 1.8808158,
                  jupiter: 11.862615,
                  saturn: 29.447498,
                  uranus: 84.016846,
                  neptune: 164.79132
                }

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def on_earth
    on_earth_precision.round(2)
  end

  def on_mercury
    (on_earth_precision/EARTH_YEARS[:mercury]).round 2
  end

  def on_venus
    (on_earth_precision/EARTH_YEARS[:venus]).round 2
  end

  def on_mars
    (on_earth_precision/EARTH_YEARS[:mars]).round 2
  end

  def on_jupiter
    (on_earth_precision/EARTH_YEARS[:jupiter]).round 2
  end

  def on_saturn
    (on_earth_precision/EARTH_YEARS[:saturn]).round 2
  end

  def on_uranus
    (on_earth_precision/EARTH_YEARS[:uranus]).round 2
  end

  def on_neptune
    (on_earth_precision/EARTH_YEARS[:neptune]).round 2
  end

  private

  def on_earth_precision
    @seconds / 31557600
  end
end

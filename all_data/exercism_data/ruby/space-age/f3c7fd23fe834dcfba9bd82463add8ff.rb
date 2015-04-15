class SpaceAge

  attr_writer :age_in_seconds
  attr_reader :planets

  def initialize(seconds)
    @age_in_seconds = seconds
    @planets = earth_day_equivalent
  end

  def seconds
    @age_in_seconds
  end

  def on_earth
    age_calculator(:earth)
  end

  def on_mercury
    age_calculator(:mercury)
  end

  def on_venus
    age_calculator(:venus)
  end

  def on_mars
    age_calculator(:mars)
  end

  def on_jupiter
    age_calculator(:jupiter)
  end

  def on_saturn
    age_calculator(:saturn)
  end

  def on_uranus
    age_calculator(:uranus)
  end

  def on_neptune
    age_calculator(:neptune)
  end

  private

  def age_calculator(planet)
    planetary_equivalent = earth_day_equivalent[planet]
    in_planet_years = @age_in_seconds/60/60/24/planetary_equivalent
    in_planet_years.round(2)
  end

  def earth_day_equivalent
    {
        earth: 365.25,
        mercury: 87.965,
        venus: 224.68,
        mars: 686.98,
        jupiter: 4332.5955,
        saturn: 10758.804,
        uranus: 30706.5675,
        neptune: 60196.8525,
    }
  end
end

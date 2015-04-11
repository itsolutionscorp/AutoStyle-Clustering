class SpaceAge
  $PLANET_YEAR_RATIOS = {
    earth: 1.0,
    mercury: 0.2408467,
    venus: 0.61519726 ,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132,
  }

  $EARTH_YEAR_SECONDS = 31557600.0

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  private
  def method_missing(method_name, *args, &block)
    case 
      when method_name.match(/^on_(.*)$/)
        on($1)

    else
      super

    end
  end

  private
  def on(planet)
    (@seconds / planet_year_length_in_seconds(planet)).round(2)
  end

  private
  def planet_year_length_in_seconds(planet)
    $PLANET_YEAR_RATIOS[planet.intern] * $EARTH_YEAR_SECONDS
  end

end

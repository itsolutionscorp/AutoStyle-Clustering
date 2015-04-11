class SpaceAge
  YEAR_ON_SEC = 31557600

  YEAR_ON_PLANET = {
                    :earth   => 1          ,
                    :mercury => 0.2408467  ,
                    :venus   => 0.61519726 ,
                    :mars    => 1.8808158  ,
                    :jupiter => 11.862615  ,
                    :saturn  => 29.447498  ,
                    :neptune => 164.79132  ,
                    :uranus  => 84.016846   }

  def initialize seconds
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def calc period
    @seconds.fdiv(YEAR_ON_SEC * period).round(2)
  end

  def on_earth
    calc YEAR_ON_PLANET[:earth]
  end

  def on_mercury
    calc YEAR_ON_PLANET[:mercury]
  end
  
  def on_venus
    calc YEAR_ON_PLANET[:venus]
  end

  def on_mars
    calc YEAR_ON_PLANET[:mars]
  end

  def on_jupiter
    calc YEAR_ON_PLANET[:jupiter]
  end

  def on_saturn
    calc YEAR_ON_PLANET[:saturn]
  end

  def on_neptune
   calc YEAR_ON_PLANET[:neptune]
  end

  def on_uranus
   calc YEAR_ON_PLANET[:uranus]
  end
end

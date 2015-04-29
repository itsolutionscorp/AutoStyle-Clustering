class SpaceAge
  def initialize(secs)
    @secs = secs.to_f
    @earth_year_in_seconds = 31557600.0
    @ratios = {earth: 1.0,
               mercury: 0.2408467,
               venus: 0.61519726,
               mars: 1.8808158,
               jupiter: 11.862615,
               saturn: 29.447498,
               uranus: 84.016846,
               neptune: 164.79132
    }
  end

  def seconds
    @secs
  end

  def on_earth
    @age = @secs/@earth_year_in_seconds
    @age.round(2)
  end

  def on_mercury
    age_on_merc = @age/@ratios[:mercury]
    age_on_merc.round(2)
  end

  def on_venus
    age_on_venus = @age/@ratios[:venus]
    age_on_venus.round(2)
  end

end

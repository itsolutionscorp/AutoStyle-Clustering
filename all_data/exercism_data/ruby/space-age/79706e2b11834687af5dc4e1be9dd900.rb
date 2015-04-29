class SpaceAge
  def initialize(seconds)
    @seconds = seconds.to_f #coverts the arg passed in to a float
    @earth_year_in_seconds = 31557600
    @ratios = { earth: 1.0,
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
    @seconds
  end

 def on_earth
   ((@seconds / @earth_year_in_seconds) / @ratios[:earth]).round(2)
 end

  def on_mercury
    ((@seconds / @earth_year_in_seconds) / @ratios[:mercury]).round(2)

  end

def on_venus
  ((@seconds / @earth_year_in_seconds) / @ratios[:venus]).round(2)

end

  def on_mars
    ((@seconds / @earth_year_in_seconds) / @ratios[:mars]).round(2)

  end

  def on_saturn
    ((@seconds / @earth_year_in_seconds) / @ratios[:saturn]).round(2)

  end

  def on_uranus
    ((@seconds / @earth_year_in_seconds) / @ratios[:uranus]).round(2)

  end

  def on_neptune
    ((@seconds / @earth_year_in_seconds) / @ratios[:neptune]).round(2)

  end
end

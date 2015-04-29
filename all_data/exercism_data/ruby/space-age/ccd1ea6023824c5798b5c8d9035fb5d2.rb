class SpaceAge
  
  def initialize(secs)
    @secs = secs.to_f
    @earth_year_in_seconds = 31557600.0
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

  define_method(:seconds){ @secs }

  [:earth, :mercury, :venus, :mars, :jupiter, :saturn, :uranus, :neptune].each do |planet|
    define_method("on_#{planet}") { self.on(planet) }
  end

  def on(planet)
    ((@secs / @earth_year_in_seconds) / @ratios[planet]).round(2)
  end

end

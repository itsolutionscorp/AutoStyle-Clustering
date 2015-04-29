class SpaceAge
  EARTH_YEAR_IN_SECONDS = 31_557_600

  GALAXY = {
    mercury: 0.2408467,
    venus: 0.61519726,
    mars: 1.8808158,
    jupiter: 11.862615,
    saturn: 29.447498,
    uranus: 84.016846,
    neptune: 164.79132
  }

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds.to_f
  end

  def on_earth
    (seconds/EARTH_YEAR_IN_SECONDS).round(2)
  end

  # on_venus 9.785 is rounded up by .round(2)
  def round_five_down(n)
    n.to_s[/\d+\.\d{3}/][-1] == "5" ? n.to_s[/\d+\.\d{2}/].to_f : n.round(2)
  end

  GALAXY.each do |key, value| 
    define_method "on_#{key}" do
      round_five_down((on_earth/value))
    end
  end

end

class SpaceAge

  attr_reader :seconds

  ratios = { "earth"   => 1.0,
               "mercury" => 0.2408467,
               "venus"   => 0.61519726,
               "mars"    => 1.8808158,
               "jupiter" => 11.862615,
               "saturn"  => 29.447498,
               "uranus"  => 84.016846,
               "neptune" => 164.79132
         }

  earthYearSecs = 31557600

  def initialize(s)
    @seconds = s  # s.gsub('_', '').to_i
  end

  ratios.each do |planet, ratio|
    define_method ("on_#{planet}") {
      return (@seconds / (ratio * earthYearSecs)).round(2)
    }
  end

end

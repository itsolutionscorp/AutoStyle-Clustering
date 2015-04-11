class SpaceAge
  attr_reader :seconds

  Seconds_in_earth_year = 31557600.0
  Period =  {
     earth:    1.0,
     mercury:  0.2408467,
     venus:    0.61519726,
     mars:     1.8808158,
     jupiter:  11.862615,
     saturn:   29.447498,
     uranus:   84.016846,
     neptune:  164.79132
  }

  def initialize seconds
    @seconds = seconds
    Period.each do |planet, period|
      self.class.send :define_method, "on_#{planet}".to_sym do
        (@seconds/Seconds_in_earth_year/period).round 2
      end
    end

  end

end

class SpaceAge
  attr_reader :seconds

  Period =  {
    earth:    31557600.0,
     mercury:  0.2408467,
     venus:    0.61519726,
     mars:     1.8808158,
     jupiter:  11.862615,
     saturn:   29.447498,
     uranus:   84.016846,
     neptune:  164.79132
  }

  def create_method(name, &block)
    self.class.send(:define_method, name, &block)
  end

  Period.each do |planet, period|
    period = 1 if planet == :earth
    define_method("on_#{planet}") do
      (@seconds/Period[:earth]/period).round 2
    end
  end

  def initialize seconds
    @seconds = seconds
  end

end

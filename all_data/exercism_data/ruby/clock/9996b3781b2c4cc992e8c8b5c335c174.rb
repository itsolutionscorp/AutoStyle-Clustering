class Clock 
	attr_reader :min
  MIN_PER_DAY = 1440

	def initialize(min)
		@min = min % MIN_PER_DAY 
	end

	def self.at(*min)
    # Clock.at() sets time to 12 midnight 
    Clock.new( (min[0] || 0) * 60 + (min[1] || 0) )
	end

  def +(min)
    self.class.new( (@min + min) % MIN_PER_DAY )
  end

  def -(min)
  	if min > @min
      self.class.new(MIN_PER_DAY - min + @min)
    else
      self.class.new(@min - min)
    end
  end

  def ==(clock)
  	@min == clock.min
  end

  def to_s  
  	"%02d:%02d" % ["#{@min / 60}","#{@min % 60}"]
  end
end

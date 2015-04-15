class Clock

	attr_reader :hour, :minute

	def initialize(hour=0, minute=0)
		@hour = hour
		@minute = minute
	end
	
	def self.at(hour, minute=0)
		Clock.new(hour, minute)
	end
	
	def to_s
		if 	@hour < 10 
			h = '0' + @hour.to_s
		else
			h = @hour.to_s
		end
		if 	@minute < 10
			min = '0' + @minute.to_s
		else 
			min = @minute.to_s
		end
		h + ':' + min
	end
	
	def +(minute)
		h_min = minute.divmod(60)
		@hour = (@hour + h_min[0]) % 24
		@minute += h_min[1]
		Clock.new(@hour, @minute)
	end
	
	def -(minute)
		h_min = minute.divmod(60)
		@hour = (@hour - h_min[0]) % 24
		if h_min[1] > @minute then 			
			@hour = (@hour -1) % 24
		end
		@minute = (@minute - h_min[1]) % 60
		Clock.new(@hour, @minute)
	end
	
	def <=>(other)
		[self.hour.to_i, self.minute.to_i] <=> [other.hour.to_i, other.minute.to_i]
	end
	
	def ==(other)
		[self.hour.to_i, self.minute.to_i] == [other.hour.to_i, other.minute.to_i]
	end
	alias_method :eql?, :==
end

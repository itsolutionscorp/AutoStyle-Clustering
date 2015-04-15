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
		"%02d:%02d" % [self.hour, self.minute]
	end
	
	def +(minute)
		h_min = minute.divmod(60)
		@hour = (self.hour + h_min[0]) % 24
		@minute += h_min[1]
		Clock.new(@hour, @minute)
	end
	
	def -(minute)
		h_min = minute.divmod(60)
		@hour = (hour - h_min[0]) % 24
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

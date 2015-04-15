class Clock
	def self.at(hr, min = 0)
		new(to_minutes(hr, min))
	end
	
	def initialize(minutes)
		@minutes = minutes
	end
	
	def to_s
		"%02d:%02d" % [hour, minute]
	end
	
	def +(offset)
		Clock.new(@minutes + offset)
	end
	
	def -(offset)
		Clock.new(@minutes - offset)
	end
	
	def ==(other)
		self.to_s == other.to_s
	end

private
	def hour
		(@minutes / 60).modulo(24)
	end
	
	def minute
		@minutes.modulo(60)
	end
	
	def self.to_minutes(hr, min = 0)
		(hr * 60) + min
	end
end

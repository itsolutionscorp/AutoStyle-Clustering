class Clock
	def self.at(hr, min = 0)
		new(hr, min)
	end
	
	def initialize(hr, min = 0)
		@minutes = (hr * 60) + min
	end
	
	def to_s
		"#{'%02d' % hour}:#{'%02d' % minute}"	
	end
	
	def +(offset)
		@minutes += offset
		self
	end
	
	def -(offset)
		@minutes -= offset
		self
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
end

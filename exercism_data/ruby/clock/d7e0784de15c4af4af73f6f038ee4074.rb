class Clock

	def initialize(hour, minutes=0)
		@hour = hour
		@minutes = minutes
	end

	def self.at(hour, minutes=0)
		new(hour, minutes)
	end

	def to_s
		format('%02d', @hour) + ':' + format('%02d', @minutes)
	end

	def +(minutes)
		hours = minutes / 60
		minutes = minutes % 60
		@hour += hours
		@hour = @hour % 24
		@minutes += minutes
		@minutes = @minutes % 60
		self
	end

	def -(minutes)
		hours = minutes / 60
		minutes = minutes % 60
		@hour -= hours
		@hour = @hour % 24
		@minutes -= minutes
		@hour -= 1 if @minutes < 0
		@minutes = @minutes % 60
		self
	end

	def ==(other)
		self.to_s == other.to_s
	end

end

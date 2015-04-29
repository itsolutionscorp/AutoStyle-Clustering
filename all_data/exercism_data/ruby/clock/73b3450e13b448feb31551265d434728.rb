class Clock
	attr_reader :hour, :minutes

	def initialize(hour, minutes)
		self.hour = hour
		self.minutes = minutes
	end

	def to_s
		hour.to_s.rjust(2, "0") + ":" + minutes.to_s.rjust(2, "0")
	end

	def hour=(hour)
		@hour = hour % 24
	end

	def minutes=(minutes)
		if minutes > 0
			while minutes > 60
				self.hour += 1
				minutes -= 60
			end
		else
			while minutes < 0
				self.hour -= 1
				minutes += 60
			end
		end

		@minutes = minutes
	end

	def +(minutes)
		Clock.at(hour, self.minutes + minutes)
	end

	def -(minutes)
		Clock.at(hour, self.minutes - minutes)
	end

	def ==(other)
		self.hour == other.hour && self.minutes == other.minutes
	end

	def self.at(h, m=0)
		Clock.new(h, m)
	end
end

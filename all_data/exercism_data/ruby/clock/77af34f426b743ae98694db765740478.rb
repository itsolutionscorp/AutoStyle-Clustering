class Clock
	attr_reader :minutes

	def initialize(hour, minutes=0)
		@minutes = (hour*60 + minutes) % (24*60)
	end

	def to_s
		"%02d:%02d" % minutes.divmod(60)
	end

	def +(minutes)
		h, m = self.minutes.divmod(60)
		Clock.at(h, m + minutes)
	end

	def -(minutes)
		self + -minutes
	end

	def ==(other)
		minutes == other.minutes
	end

	def self.at(*args)
		new(*args)
	end
end

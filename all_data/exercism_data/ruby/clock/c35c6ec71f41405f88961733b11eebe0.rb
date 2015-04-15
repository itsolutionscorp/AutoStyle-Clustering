class Clock

	def initialize(min)
		@minutes = min
	end

    def self.at(hour, minute=0)
		new(hour*60 + minute)
	end

	def to_s
		@minutes %= (60*24)
		"%02d:%02d" % [ @minutes/60, @minutes%60 ]
	end

	def +(num)
		Clock.new(@minutes + num)
	end

	def -(num)
		self + -num
	end

	def ==(time)
		to_s == time.to_s
	end

end

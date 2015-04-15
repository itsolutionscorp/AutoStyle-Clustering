class Gigasecond
	def initialize(d)
		@d = d
	end

	def date
		@d + (10**9/86400)
	end
end

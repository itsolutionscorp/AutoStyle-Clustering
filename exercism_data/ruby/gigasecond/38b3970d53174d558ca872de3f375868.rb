class Gigasecond
	def self.from(a)
		jump = 1000000000 / (60*60*24) #60s * 60m * 24h
		gs = a + jump
	end
end

class Gigasecond
	def self.from( birthday )
		days = 1000000000 / 60 / 60 / 24 
		return birthday + days
	end
end

class Gigasecond
	GIGASECOND_IN_DAYS = 11574
	def self.from(birthDate)
		birthDate + GIGASECOND_IN_DAYS
	end
end

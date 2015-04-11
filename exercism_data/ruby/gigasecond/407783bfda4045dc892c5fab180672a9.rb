class Gigasecond
	def self.from(start_date)
		start_date + Rational(1000000000 - 6400, 86400)
	end
end

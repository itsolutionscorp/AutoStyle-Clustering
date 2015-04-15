class Gigasecond
	def self.from(a)
		b = a.to_datetime + Rational((10**9), 86400)
		b.to_date
	end
end

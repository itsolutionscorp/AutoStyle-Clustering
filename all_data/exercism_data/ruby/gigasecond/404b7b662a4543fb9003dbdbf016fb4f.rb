class Gigasecond
	def self.from(a)
		b = DateTime.new(a.year, a.mon, a.mday) + Rational(10**9, 86400)
		return b.to_date
	end
end

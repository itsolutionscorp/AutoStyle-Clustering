class Gigasecond

	def self.from(date)
		return date.getGigasecondAnniversary(1)
	end

end

# gigasecons anniversary conversion
class Numeric

	def toGigaseconds
		return self * 1_000_000_000
	end

end

# date and time modifications to support calculation directly in the object itself
class Date

	$one_day_in_seconds = 86400

	def getGigasecondAnniversary(anniversary)
		return self + (anniversary.toGigaseconds / $one_day_in_seconds)
	end

end

class Time

	def getGigasecondAnniversary(anniversary)
		return self + anniversary.toGigaseconds
	end

end

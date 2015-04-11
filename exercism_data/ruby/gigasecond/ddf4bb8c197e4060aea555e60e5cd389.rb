class Gigasecond
	GIGASECOND = 10**9
	def self.from(from)
		(from.to_time + GIGASECOND).to_date
	end
end

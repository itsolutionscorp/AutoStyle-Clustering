class Gigasecond
	def self.from(dt)
		dt = dt.to_time
		dt += (10**9)
		dt = dt.to_date
	end
end

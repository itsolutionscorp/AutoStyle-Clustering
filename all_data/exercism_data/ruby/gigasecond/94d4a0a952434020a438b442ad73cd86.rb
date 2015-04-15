class Gigasecond
	def self.from(value)
		return (value.to_time + 10**9).to_date
	end
end

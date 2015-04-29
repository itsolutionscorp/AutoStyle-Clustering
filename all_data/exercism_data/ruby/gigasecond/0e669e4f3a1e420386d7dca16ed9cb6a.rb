class Gigasecond
	GIGASECOND = 1e9

	def self.from(day)
		day_timestamp = day.to_time.to_i
		day_timestamp = day_timestamp + GIGASECOND
		Date.strptime(day_timestamp.to_s, '%s')
	end
end

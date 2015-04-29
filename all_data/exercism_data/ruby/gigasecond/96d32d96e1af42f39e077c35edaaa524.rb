class Gigasecond
	def self.from(start_date)
		time = Time.parse(start_date.to_s)
		time += 10**9
		return Date.parse(time.to_s)
	end
end

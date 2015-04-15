class Gigasecond
	def self.from(date)
    time = date.to_time
    gigasecond_time = time + 1000000000
    gigasecond_date = gigasecond_time.to_date
	end
end

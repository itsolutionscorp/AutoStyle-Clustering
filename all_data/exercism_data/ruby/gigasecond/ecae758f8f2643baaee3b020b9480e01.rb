class Gigasecond
	def self.from(date)
		time = date.to_time + 1_000_000_000
		DateTime.new(time.year,time.month,time.day)
	end
end

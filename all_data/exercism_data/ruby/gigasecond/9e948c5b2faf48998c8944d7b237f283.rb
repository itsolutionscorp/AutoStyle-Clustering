class Gigasecond
	def self.from(start_date)
		gigasecond = 10**9
		(start_date.to_time + gigasecond).to_date
	end
end

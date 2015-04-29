class Gigasecond
	def self.from(start_date)
		(start_date.to_time + 10**9).to_date
	end
end

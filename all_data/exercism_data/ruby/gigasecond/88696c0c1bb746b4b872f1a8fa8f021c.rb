class Gigasecond
	def self.from(startdate)
		(startdate.to_time + 10**9).to_date
	end
end

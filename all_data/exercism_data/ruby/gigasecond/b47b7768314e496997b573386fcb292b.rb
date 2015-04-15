class Gigasecond
	def self.from(start_date)
		time = start_date.to_time
		gig_time = time + (10**9)
		gig_time.to_date
	end
end

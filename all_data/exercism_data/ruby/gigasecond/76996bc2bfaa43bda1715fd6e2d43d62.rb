class Gigasecond
	def self.from(date)
		# Calculate date one gigasecond from input date.
		GIGA = 10**9
		
		(date.to_time + GIGA).to_date
	end
end

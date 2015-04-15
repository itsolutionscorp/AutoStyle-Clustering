class Gigasecond
	def self.from(input_date)
		# convert the input to time, add seconds and then convert back to date
		(input_date.to_time + (10**9)).to_date
	end
end

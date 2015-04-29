class Gigasecond
	def self.from(date)
		# Take date, turn into seconds (time), add 10**9, turn into date
		return (date.to_time + 10**9).to_date
	end
end

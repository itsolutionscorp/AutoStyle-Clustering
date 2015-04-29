class Gigasecond
	# return a date 1 billion seconds (a Gigasecond) from the given date.
	def self.from(d)
		return (d.to_time+10**9).to_date
	end
end

class Gigasecond
	def self.from(date)
		giga = 10**9

		# Return gigasecond anniversary date
		return (date.to_time + giga).to_date
	end
end

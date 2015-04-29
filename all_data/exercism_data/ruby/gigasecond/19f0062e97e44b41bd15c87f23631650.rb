class Gigasecond
	@gigasecond_in_days = 1000000000/60/60/24

	def self.from(date)
		return date + @gigasecond_in_days
	end
end

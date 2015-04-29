class Gigasecond
	def self.from(date)
		date=(date+1).to_datetime if date.class==Time
		sec_of_day = 24*3600
		days = 10**9 / sec_of_day
		date += days
		Date.new(date.year, date.month, date.day)
	end
end

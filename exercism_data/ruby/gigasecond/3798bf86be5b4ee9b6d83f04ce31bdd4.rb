class Gigasecond
	def self.from(birthday_day)
		date = birthday_day.to_time
		date = date + 1000000000
		date = date.to_date
	end
end

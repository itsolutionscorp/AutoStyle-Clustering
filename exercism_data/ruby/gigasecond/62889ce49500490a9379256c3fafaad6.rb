class Gigasecond
	def self.from(date)
		time_distance = Rational(1000000000,86400)
		computed_date = date + time_distance
		computed_date.to_datetime.to_date
	end
end

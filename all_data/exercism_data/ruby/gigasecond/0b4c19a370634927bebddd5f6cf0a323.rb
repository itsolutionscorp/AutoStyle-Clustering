class Gigasecond
	def self.from(date)
		gigaseconds = 10**9
		return (date.to_time + gigaseconds).to_datetime.to_date
	end
end

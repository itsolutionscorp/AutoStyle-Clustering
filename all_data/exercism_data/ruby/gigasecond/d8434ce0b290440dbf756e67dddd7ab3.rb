class Gigasecond
	def initialize(date)
		@date = date
	end

	def date
		return Date.parse((@date.to_time + 1000000000).to_s)
	end
end

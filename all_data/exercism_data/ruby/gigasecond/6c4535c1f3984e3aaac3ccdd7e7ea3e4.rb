class Gigasecond
	def initialize(date)
		@date = date
	end

	def date
		seconds = 1000000000
		months = 380.265
        time = @date.to_time + seconds
        Date.parse(time.strftime('%Y/%m/%d'))
	end
end

class Gigasecond
	def initialize(date)
		@original_date = date
	end

	def date
		gigadate = Date.jd(@original_date.jd + 11574)
	end

end

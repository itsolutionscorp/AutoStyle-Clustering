class Gigasecond
	def initialize(date)
		@date = date
	end
	def date
		return (@date.to_time + 10**9).to_date
	end
end

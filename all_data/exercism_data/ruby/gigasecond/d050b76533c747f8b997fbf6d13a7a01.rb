require 'date'

class Gigasecond
	def initialize date
		@date = date
    @seconds = 10 ** 9
	end

	def date
    (@date.to_time + @seconds).to_date
	end
end

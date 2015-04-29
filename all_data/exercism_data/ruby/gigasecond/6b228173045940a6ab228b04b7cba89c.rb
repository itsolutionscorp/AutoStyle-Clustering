require 'date'
require 'time'

class Gigasecond
	def initialize(date)
		year = date.year
		month = date.month
		day = date.day
		@time = Time.new(year,month,day)
	end

	def date
		@time = @time + (1000000000)
		return @time.to_date
	end
end

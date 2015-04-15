require 'date'
require 'time'

class Gigasecond
	def initialize(date)
		year = date.year
		month = date.month
		day = date.day
		@birthday = Time.new(year, month, day)
	end

	def date
		@anniversary = @birthday + (10**9)
		return @anniversary.to_date
	end
end

require 'date'
require 'time'
class Gigasecond

	def initialize(birthday)
		@birthday = birthday.to_time
	end

	def date #Enter Date.new(year,month,day)
		gday = @birthday.to_time + 10**9

		return gday.to_date	
	end

end

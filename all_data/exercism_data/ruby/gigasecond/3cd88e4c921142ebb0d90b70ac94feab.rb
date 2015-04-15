require 'date'
require 'time'

class Gigasecond
	def self.from(begin_date)
		#convert to datetime
		begin_datetime = Time.new(begin_date.year, begin_date.month, begin_date.day)
		return (begin_datetime + (10**9)).to_date
	end
end

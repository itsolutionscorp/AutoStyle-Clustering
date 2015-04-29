require 'date'
class Gigasecond
	#attr_accessor :year, :month, :day
	def initialize(year,month,day)
		@year = year
		@month = month
		@day = day
	end
	def calculate
		birthday = Date.new(@year, @month, @day)
		self.class.from(birthday)
	end
	def self.from(date) 
		(date.to_time + 10**9).to_date
	end
end

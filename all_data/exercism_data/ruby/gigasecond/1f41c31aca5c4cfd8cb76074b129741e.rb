class Gigasecond
	attr_reader :input

	def initialize(input)
		@date = input
	end

	def date
		giga_date = @date.to_datetime + (10**9)/86400
		return Date.new(giga_date.year, giga_date.month,giga_date.day)
	end

end

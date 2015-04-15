require 'date'
class Year
	attr_accessor :date

	def initialize(year)
		@date = Date.parse("#{year}/01/01")
	end

	def leap?
		date.leap?
	end

end

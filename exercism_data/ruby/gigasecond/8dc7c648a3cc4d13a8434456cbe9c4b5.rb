require 'date'
require 'time'

class Gigasecond

	def initialize(birthday)
		@date = birthday + ((10 ** 9)/(60 * 60 * 24))
	end

	attr_reader :date

end

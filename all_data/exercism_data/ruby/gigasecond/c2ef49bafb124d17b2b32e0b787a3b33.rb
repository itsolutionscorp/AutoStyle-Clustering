class Gigasecond
	GIGASECOND_IN_YEARS = 11574
	attr_reader :date

	def initialize(birthdate)
		@date = get_anniversary(birthdate)
	end

	def get_anniversary(birthdate)
		birthdate + GIGASECOND_IN_YEARS
	end
end

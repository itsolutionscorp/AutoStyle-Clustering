class Gigasecond
	def initialize(birthday)
		@birthday = birthday
		@billion_seconds_in_days = (1000000000/86400)
	end
	
	def date
		@birthday + @billion_seconds_in_days
	end
end

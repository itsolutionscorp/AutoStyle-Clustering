class Gigasecond
	def initialize(newdate)
		@birthdate = newdate
	end
	def date
		@birthdate = @birthdate.to_time
		gigsec = @birthdate + (1000000000)
		gigsec = gigsec.to_date
	end
end

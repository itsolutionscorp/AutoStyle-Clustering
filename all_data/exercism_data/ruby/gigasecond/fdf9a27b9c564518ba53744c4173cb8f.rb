class Gigasecond
	attr_accessor :date
	def initialize(start_date)
		@date = 0
		gsecs = 10**9
		#Convert seconds to days
		gdays = gsecs/60/60/24
		@date = start_date + gdays
	end
end

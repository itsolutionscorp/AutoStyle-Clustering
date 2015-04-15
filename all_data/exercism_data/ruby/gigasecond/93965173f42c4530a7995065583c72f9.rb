class Gigasecond
	@date = Date.today
	def initialize(date)
		@date = date
	end
	def date
		gs = 10**9
		gs = gs/60/60/24
		@date = @date + gs
		@date
	end
end

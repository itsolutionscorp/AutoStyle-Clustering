class Gigasecond

	GS_IN_DAYS = ((10**9)/(60*60*24))

	def initialize(start_date)
		self.start_date = start_date
	end

	def date
		start_date + GS_IN_DAYS
	end

	private
		attr_accessor :start_date
end

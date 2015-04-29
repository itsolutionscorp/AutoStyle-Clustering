class Gigasecond

	def initialize(start_date)
		self.start_date = start_date
	end

	def date
		start_date + ((10**9)/(60*60*24))
	end

	private
		attr_accessor :start_date
end

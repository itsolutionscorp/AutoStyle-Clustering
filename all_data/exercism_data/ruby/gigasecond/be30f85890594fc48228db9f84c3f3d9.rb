class Gigasecond
	attr_accessor :start_date

	def initialize(start_date)
		self.start_date = start_date
	end

	def date
		gigasecond_time_from(self.start_date).to_date
	end

	def gigasecond_time_from(date)
		date.to_time + 10**9
	end
end

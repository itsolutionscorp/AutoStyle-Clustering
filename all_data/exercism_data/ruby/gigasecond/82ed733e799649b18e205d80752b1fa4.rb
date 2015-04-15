class Gigasecond
	attr_accessor :date
	def initialize(x_date)
		self.date = Time.at(x_date.to_time.to_i + (10**9)).to_date
	end
end

class Gigasecond
	def initialize(d)
		d += 1e9/3600/24
		@date = d-d.day_fraction
	end
	attr_reader :date
end

class Gigasecond
	def initialize(d)
		@date = d+(1e9/3600/24).floor
	end
	attr_reader :date
end

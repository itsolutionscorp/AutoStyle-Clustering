class Gigasecond
	def initialize(d) @date = d + 10**9 / 24 / 3600 end #add a billion seconds in days
	attr_reader :date
end

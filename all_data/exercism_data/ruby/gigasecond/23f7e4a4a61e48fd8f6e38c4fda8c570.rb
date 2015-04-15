class Gigasecond
	def initialize(d) @date = d end
	def date() @date + 10**9 / 24 / 3600 end #add a billion seconds in days
end

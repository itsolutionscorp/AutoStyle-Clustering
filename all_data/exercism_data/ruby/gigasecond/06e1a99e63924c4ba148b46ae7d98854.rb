class Gigasecond
	# return a date 1 billion seconds (a Gigasecond) from the given date.
	def self.from(d)
		dt = Time.parse d.to_s
		return Date.parse (dt+1000000000).to_s
	end
end

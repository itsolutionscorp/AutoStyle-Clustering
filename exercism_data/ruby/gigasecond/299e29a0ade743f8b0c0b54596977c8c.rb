class Gigasecond
	def self.from(d)
		dt = Time.parse d.to_s
		return Date.parse (dt+1000000000).to_s
	end
end

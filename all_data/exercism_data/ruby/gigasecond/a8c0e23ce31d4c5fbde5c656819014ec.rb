class Gigasecond
	def self.from(date)
		t = Time.parse(date.to_s)
		gs = t + 10 ** 9
		return Date.parse(gs.to_s)
	end
end

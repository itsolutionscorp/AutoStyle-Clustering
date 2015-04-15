class Gigasecond
	def self.from(a)
		Time.at(a.to_f + 1000000000).utc
	end
end

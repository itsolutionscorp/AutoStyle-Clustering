class Gigasecond
	def self.from(date)
		date += 11574 # 1,000,000,000 / 60 / 60 / 24 - convert seconds to days
	end
end

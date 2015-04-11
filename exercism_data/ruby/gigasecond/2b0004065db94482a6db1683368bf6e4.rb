class Gigasecond
	Gigasec = 10**9
	def self.from(date)
			date + Gigasec/(3600*24)
	end
end

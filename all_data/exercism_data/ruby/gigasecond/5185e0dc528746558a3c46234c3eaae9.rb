class Gigasecond
		
	def self.from(date)
		Time.at((date).utc + 10**9)
	end

end

class Gigasecond
	def self.from(dob)
		return dob+((10**9).div(86400))
	end
end

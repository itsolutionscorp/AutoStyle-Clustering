class Gigasecond
	def self.from ( fromDate )
		aGigaSecond = 1E9
		(fromDate.to_time + aGigaSecond).to_date
  	end
end

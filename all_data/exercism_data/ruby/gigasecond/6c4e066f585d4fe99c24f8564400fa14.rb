class Gigasecond
	def self.gigasecond() 10**9 end
	
	def self.from(date)
		(date.to_time + gigasecond).to_date
	end
end

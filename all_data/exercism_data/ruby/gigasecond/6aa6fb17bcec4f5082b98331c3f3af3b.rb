class Gigasecond
	def self.from(from_date)
		if from_date.kind_of?(Date) then 
		  from_date + self.gigaseconds_in_days
		else
			(from_date + self.gigasecs).to_date
		end
	end

	def self.gigaseconds_in_days
		self.gigasecs / (60 * 60 * 24)
	end

	def self.gigasecs
		(10**9)
	end
end

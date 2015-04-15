class Gigasecond
	@@giga = 1_000_000_000


	def self.from(date)
		(date.to_time + @@giga).to_date
	end
end

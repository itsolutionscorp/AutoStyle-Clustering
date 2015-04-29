class Gigasecond

	@@GIGA_SECOND_OFFSET = 10**9

	def self.from(start_date)
		(start_date.to_time + @@GIGA_SECOND_OFFSET).to_date
	end

end

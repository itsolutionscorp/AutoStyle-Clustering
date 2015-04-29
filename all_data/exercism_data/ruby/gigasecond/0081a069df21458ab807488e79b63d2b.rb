class Gigasecond

	def self.from(date)
		new_date = (date.to_time + 1000000000).to_date
		new_date
		
	end


end

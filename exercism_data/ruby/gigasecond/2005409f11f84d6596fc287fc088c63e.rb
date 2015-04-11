class Gigasecond

	def self.from(start)
		(start.to_time + 10**9).to_date
	end

end

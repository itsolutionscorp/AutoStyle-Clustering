class Gigasecond
	def self.from(gs)
		(gs.to_time + 10**9).to_date
	end
end

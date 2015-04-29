class Gigasecond
	def self.from x
		(x.to_time + 10**9).to_date
	end
end

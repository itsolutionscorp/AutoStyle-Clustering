class Gigasecond
	def self.from from_date
		from_date + ((10**9) / (60 * 60 * 24))
	end
end

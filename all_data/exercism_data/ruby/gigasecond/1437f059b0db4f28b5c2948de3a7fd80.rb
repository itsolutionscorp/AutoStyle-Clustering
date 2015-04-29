class Gigasecond
	def self.from start_date
		start_date + ((10**9) / (60 * 60 * 24)) # date + days in 1 billion seconds OR ( 1 billion seconds / seconds in a day )
	end
end

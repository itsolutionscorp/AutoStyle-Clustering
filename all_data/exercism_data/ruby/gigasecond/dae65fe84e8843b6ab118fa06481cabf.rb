class Gigasecond
	#what is the date 1 billion seconds from now?
	def self.from(date)
		gigasecond = 10**9
		days = (10**9)/60/60/24
		date.is_a?(Date) ? date+days : (date+gigasecond).to_date
	end
end

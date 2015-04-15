class Gigasecond
	GIGASECOND = 10**9
	DAYS_IN_GIGASEC = GIGASECOND/60/60/24 #60 sec, 60 min, 24 hrs
	
	def self.from(date)
		return false unless date.instance_of? Date
		date + DAYS_IN_GIGASEC
	end
end

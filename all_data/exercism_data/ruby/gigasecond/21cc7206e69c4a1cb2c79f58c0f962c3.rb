class Gigasecond 

 def self.from(date)
	gigasec_to_days = self.seconds_to_days(10**9)
	date + gigasec_to_days
 end
 
 def self.seconds_to_days(seconds)
	seconds/(60*60*24)
 end

end

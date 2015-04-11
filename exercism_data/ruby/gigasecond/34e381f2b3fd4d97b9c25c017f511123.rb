class Gigasecond 

 def self.from(birth_date)
	gigasec_to_days = self.seconds_to_days(ONE_GIGASECOND)
	birth_date + gigasec_to_days
 end
 
 private
 ONE_GIGASECOND = 10**9
 DAY_IN_SECONDS = 60*60*24
 
 def self.seconds_to_days(seconds)
	seconds/(DAY_IN_SECONDS)
 end

end

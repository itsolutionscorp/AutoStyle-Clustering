class Gigasecond
    GIG_SEC = (10**9)
	def self.from(req_date)
	    req_time = (req_date.to_time) + GIG_SEC#/24*60*60	
		req_time.to_date
	end
end

class Gigasecond
    def self.from(day_of_birth)
		gig_second = 1000000000
    	days_per_gigasecond = gig_second / 60 / 60 / 24

    	# check format of day of birth and add gigaseconds appropriately 
    	if day_of_birth.is_a? Date 
    		gig_birthday = day_of_birth + days_per_gigasecond
    	elsif day_of_birth.is_a? Time
    	    gig_birthday = day_of_birth + gig_second 
    	    gig_birthday.to_date
    	else print "Not a valid birth date"
    	end
        
    end
end

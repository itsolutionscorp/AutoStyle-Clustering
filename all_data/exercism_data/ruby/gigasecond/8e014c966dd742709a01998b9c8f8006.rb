class Gigasecond
	def self.from(date)
		date_in_seconds = date.to_time.to_i
		gs_anniversary = Time.at( date_in_seconds + (10**9) ) 

		puts "Your gigasecond anniversary will be on: 
		#{ gs_anniversary.strftime('%A %B %d, %Y at %I:%M%p') }"
		return gs_anniversary.to_date
	end
end

# birthday = Gigasecond.new
# birthday.from(1997, 3, 9)
# ==> "Your gigasecond anniversary will be on Wednesday November 15, 2028 at 01:46AM"



# For future reference -- There is a difference between Ruby "Time" & "Date"
# Time is its own class, while Date is an entirely different class
# Time will be very specific, and will always ouput the hours/minues/date_in_seconds
# Date only output year/month/day
# You can not interact a date with a time. They both have to be one or the other

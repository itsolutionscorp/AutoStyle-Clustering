class Gigasecond

	require 'date'
 
	def self.from(year, month, date, hours=0, minutes=0)
		birthday = Time.new(year, month, date, hours, minutes)
		anniversary = birthday + (10**9)
		return anniversary.to_date
	end
	
end

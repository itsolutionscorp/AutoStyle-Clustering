require 'date'
class Gigasecond
	def self.from(date)		
		return (date + (10**9/86400))
	end
end

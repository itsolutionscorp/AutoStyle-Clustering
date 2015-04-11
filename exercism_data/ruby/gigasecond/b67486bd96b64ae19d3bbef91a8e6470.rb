#Class Gigasecond returns the Date object for a given Date + a GIGASECOND (10^9)
require 'date'
require 'time'
class Gigasecond
	GIGASECOND = 1000000000
	SEC_DAY = 86400
	def self.from( birth )

		remaining = GIGASECOND
		seconds=0

		if birth.is_a? Time then
			seconds = birth.hour*3600 + birth.min*60 + birth.sec
			birth = Date.parse(birth.to_s)
		end

			
		while remaining + seconds >= SEC_DAY  do
			birth = birth+1
			remaining-=SEC_DAY
		end
		birth

	end

end

require 'date'

class Gigasecond

	# useful constants: different representations of a gigasecond
	SECONDS = 10 ** 9 		# seconds in a gigasecond
	MINUTES = SECONDS / 60  # minutes in a gigasecond
	HOURS = MINUTES / 60 	# hours in a gigasecond
	DAYS = HOURS / 24 		# days in a gigasecond; this is the resolution we need

	# note this is correct up to a resolution of days
	# it will not be correct up to the hour, minute or second
	# due to rounding errors in the constants above
	def self.from(date) 
		return date + DAYS
	end
end

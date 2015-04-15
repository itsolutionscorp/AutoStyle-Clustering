require 'date'
require 'time'

class Gigasecond
	def self.from(birthdate)
		gigabirthday = (birthdate + self.gigasecond_to_days(10.0**9))
		return Date.new(gigabirthday.year,gigabirthday.month,gigabirthday.mday)
	end

	def self.gigasecond_to_days(seconds)
		days = (((seconds / 60.0) / 60.0)/24.0)
		return days
	end
end

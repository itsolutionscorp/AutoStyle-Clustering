require 'Date'

class Gigasecond
	GIGASECOND = 10**9
	SECONDS_IN_A_DAY = 60*60*24
  GIGASECOND_IN_DAYS = GIGASECOND/SECONDS_IN_A_DAY

	def self.from(birthday)
		self.days_in(birthday) + GIGASECOND_IN_DAYS
	end

	def self.days_in(birthday)
		birthday
	end

end

module Gigasecond
  GIGASECOND_TO_DAYS = 10**9 / (24*60*60) # billion seconds divided by number of seconds in one day
	def self.from(date)
    date + GIGASECOND_TO_DAYS
	end
end

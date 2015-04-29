#Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.
#A gigasecond is one billion (10**9) seconds.
require 'date'

#get a birthdate
#add one billion seconds
class Gigasecond
	def self.from(birthdate)
		(birthdate.to_time+(10**9)).to_date
	end
end


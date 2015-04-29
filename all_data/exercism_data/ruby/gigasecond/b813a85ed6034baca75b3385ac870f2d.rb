# author: patsul12
# issues: test #4 will not pass, it is off by exactly one day and i believe this is due to some sort of rounding,
# issue with the Time object being used. If anyone has a fix let me know.

class Gigasecond
	def self.from(date)
		time = Date.strptime(date.to_s, "%Y-%m-%d")
		time += Rational((10**9 - 6400), 86400)
	end
end

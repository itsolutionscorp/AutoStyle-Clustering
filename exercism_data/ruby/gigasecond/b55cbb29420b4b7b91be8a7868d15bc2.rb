require 'date'

class Gigasecond

	def self.from(birthday)

		if birthday.class == Time 
			days = birthday.to_s.split(" ")
			days = days[0].split("-")
			birthday = Date.new(days[0].to_i, days[1].to_i, days[2].to_i + 1)
		end
		gs_days = 10**9 /60/60/24

		birth = birthday+ gs_days

		return birth

	end
end

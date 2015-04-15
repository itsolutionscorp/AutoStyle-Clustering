require "time"
require	"date"

class Gigasecond

	def self.from(birthday)
		gs = (birthday.to_time + (10**9)).to_date
		return gs
	end

end

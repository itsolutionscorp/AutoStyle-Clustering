module Gigasecond

	def Gigasecond.from(date)
		date + (1000000000/(3600 * 24))
	end

end

# Gigasecond Calculator
# Demannu 9/29/2014

class Gigasecond
	def self.from(birthday)
		# For semantics I'll do the math on converting a gigasecond to days
		gsDays = ((10**9/60)/60)/24
		gs = birthday + gsDays
	end
end


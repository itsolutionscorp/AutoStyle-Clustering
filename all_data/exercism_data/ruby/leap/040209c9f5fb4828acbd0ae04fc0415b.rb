class Year
	def self.leap?(year)
		divisible_by(year, 4) && (!divisible_by(year, 100) || divisible_by(year, 400))
	end

	def self.divisible_by(num, k); num % k == 0; end  #return true if num is divisible by k
end

class Year
	def self.leap?(n)
		if n % 400 == 0
			return true#{}"Yes, #{n} is a leap year"
		elsif n % 4 == 0
			unless n % 100 == 0
				return true#{}"Yes, #{n} is a leap year"
			end
		else
			return false#{}"No, #{n} is not a leap year"
		end

	end

end

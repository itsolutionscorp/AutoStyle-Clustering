class Year

	def self.leap?(yr)
		#Test is divisible by 4
		if yr % 4 == 0
			#Test is not divisible by 100 or is divisible by 400
			if (yr % 100 != 0) || (yr % 400 == 0)
				return true
			else
				return false
			end
		else
			return false
		end
	end

end

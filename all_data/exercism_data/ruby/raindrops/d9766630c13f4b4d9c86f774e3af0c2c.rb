class Raindrops
	def self.convert(number)
		result = ""
		if self.factor_3(number) == true
			result += "Pling"
		end
		if self.factor_5(number) == true
			result += "Plang"
		end
		if self.factor_7(number) == true
			result += "Plong"
		end
		if self.all(number) == 3 
			result = number.to_s
		end
		result
	end

	def self.factor_3(number)
		if number % 3 == 0
			return true
		end
	end

	def self.factor_5(number)
		if number % 5 == 0
			return true
		end
	end

	def self.factor_7(number)
		if number % 7 == 0
			return true
		end
	end

	def self.all(number)
		count = 0
		if number % 3 != 0
			count += 1
		end
		if number % 5 != 0 
			count += 1
		end
		if number % 7 != 0
			count += 1
		end
		count
	end 
end

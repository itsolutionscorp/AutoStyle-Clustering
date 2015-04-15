class Luhn
	def initialize(num)
		@num = num
	end
	
	def addends()	
		num = @num
		result = []
		is_even = false
		
		while num > 0
			num, digit = num.divmod(10)
			digit = digit * 2 if is_even
			digit -= 9 if digit > 9
			result.unshift(digit)
			
			is_even = !is_even
		end
		
		result
	end
	
	def checksum()
		addends().reduce(:+)
	end
	
	def valid?()
		checksum() % 10 == 0
	end
	
	def self.create(num)
		if !new(num).valid?
			num *= 10
			check = new(num).checksum() % 10
			num += 10 - check if check != 0
		end
		num
	end
end

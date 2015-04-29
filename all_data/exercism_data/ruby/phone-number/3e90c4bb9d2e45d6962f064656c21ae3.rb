class Phone
	def initialize(number)
		@number = number.gsub(/\D/, '')
	end

	def number
		if @number.length != 10 
			if @number.length == 11 && @number.chr == '1' then @number.slice!(0)
			else @number.replace('0000000000') end
		end
		return @number
	end

	def area_code
		return @number[0, 3]
	end

	def to_s
		self.number
		return "(" +@number[0,3] + ") " + @number[3,3]+"-"+@number[6,4]
	end


end

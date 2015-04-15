class PhoneNumber
	def initialize(number)
		@number = number.gsub(/[^0-9]/, '')

		if @number.length == 10 then
		elsif @number.length == 11 then
			if @number[0] == '1' then @number = @number[1,10] else @number = "0000000000" end
		else
			@number = "0000000000"
		end	
	end	

	def number()
		@number
	end

	def area_code()
		@number[0,3]
	end

	def to_s()
		"(%s) %s-%s" % [@number[0,3], @number[3,3], @number[6,4]]
	end
end

class PhoneNumber
	
	def initialize(number)
		@number = number
	end
	
	def number
		num = @number.gsub(/[^0-9]/,'')
		if !@number.gsub(/[^a-zA-Z]/,'').empty?
			return num = "0"*10
		end
		if num.length == 11 and num[0] == '1'
			num = num[1,10]
		end
		if num == "0" or num.length!=10
			num = "0"*10
		end
		num
	end
	
	def area_code
		num = number
		num[0,3]
	end

	def to_s
		num = number
		"(" + num[0,3] + ")" + " " + num[3,3] + "-" + num[6,4]
	end


end

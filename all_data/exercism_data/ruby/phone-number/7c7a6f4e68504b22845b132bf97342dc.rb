class PhoneNumber
	attr_reader :number
	def initialize(number)
		@number = clean_number number
	end

	def area_code
		@number.chars.take(3).join
	end

	def to_s
		"(#{area_code}) #{exchange}-#{extension}"
	end

	private

	def exchange
		@number.chars.drop(3).take(3).join
	end

	def extension
		@number.chars.drop(6).join
	end

	def clean_number(number)
		temp = number.gsub(/[^0-9]/, "")
		if temp.length == 11
			if temp.chars[0] == "1"
				temp = temp.chars.drop(1).join
			else
				temp = "0000000000"
			end
		elsif temp.length == 9
			temp = "0000000000"
		end
		temp
	end
end

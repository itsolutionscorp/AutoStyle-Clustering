class PhoneNumber
	
	attr_accessor :number

	def initialize(n)		
		n = clean(n)
		@number = valid?(n) ? n : "0000000000"
	end

	def area_code
		@number[0..2]
	end

	def to_s
		"(" << area_code << ") " << @number[3..5] << "-" << @number[6..9]
	end

	private

		def valid?(number)
			 just_digits?(number) && correct_size?(number)
		end

		def just_digits?(number)
			number =~ /^\d+$/
		end

		def correct_size?(number)
			number.size == 10
		end

		def clean(number)
			number = number.gsub(/[-()\.\s]/, "")
			number =~ /^1([\d]{10})$/ ? $1 : number
		end

end

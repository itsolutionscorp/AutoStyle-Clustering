class PhoneNumber
	
	attr_accessor :number

	def initialize(n)		
		@number = clean(n)
		@number = "0000000000" unless valid?
	end

	def area_code
		@number[0..2]
	end

	def to_s
		"(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
	end

	private

		def valid?
			 just_digits? && correct_size?
		end

		def just_digits?
			@number =~ /^\d+$/
		end

		def correct_size?
			@number.size == 10
		end

		def clean(number)
			number = number.gsub(/[-()\.\s]/, "")
			number =~ /^1([\d]{10})$/ ? $1 : number
		end

end

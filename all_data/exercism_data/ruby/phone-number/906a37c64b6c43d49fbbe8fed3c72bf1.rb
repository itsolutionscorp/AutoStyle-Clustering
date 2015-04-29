class PhoneNumber
	attr_reader :number

	def initialize(raw)
		number = raw.gsub(/\D/, '')
		if raw =~ /[^\s\d\-.()]/
			@number = "0" * 10
		elsif number.length == 10
			@number = number
		elsif number.length == 11 && number[0] == '1'
			@number = number[1..-1]
		else
			@number = "0" * 10
		end
	end

	def area_code
		@number[0, 3]
	end

	def to_s
		"(#{area_code}) #{@number[3, 3]}-#{@number[6, 4]}"
	end
end

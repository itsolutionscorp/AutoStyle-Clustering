class PhoneNumber
	attr_reader:number
	def initialize(n)
		@number = n.gsub(/[^0-9]/, '')
		@number.slice!(0) if @number.length == 11 && @number[0] == '1'
		@number = '0000000000' if @number.length != 10 || n.length > 14
	end

	def area_code
		@number.slice(0..2)
	end

	def to_s
		"(#{area_code()}) #{@number.slice(3..5)}-#{@number.slice(6..9)}"
	end
end

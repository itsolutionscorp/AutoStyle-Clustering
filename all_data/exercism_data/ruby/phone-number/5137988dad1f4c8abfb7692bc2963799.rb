class PhoneNumber

	def initialize(number)
		@phone_num = number
		sanitize
	end

	def number
		@phone_num
	end

	def area_code
		@phone_num[0..2]
	end

	def to_s
		"(#{area_code}) #{local_number}"
	end

	private

	def sanitize
		@phone_num = @phone_num.gsub(/[^\w]/, '')
		@phone_num = '0' * 10 if contains_letters? or !right_length?
		remove_US_country_code
	end

	def remove_US_country_code
		@phone_num = @phone_num[1..-1] if @phone_num.length == 11 && @phone_num.start_with?('1')
	end

	def contains_letters?
		true if @phone_num.match(/[a-zA-Z]/)
	end

	def right_length?
		true if @phone_num.length == 10 || ( @phone_num.length == 11 && @phone_num.start_with?('1') )
	end

	def local_number
		@phone_num[3..-1].insert(3,'-')
	end

end

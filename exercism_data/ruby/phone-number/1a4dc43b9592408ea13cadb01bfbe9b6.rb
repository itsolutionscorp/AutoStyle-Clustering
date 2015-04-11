class PhoneNumber
	attr_reader :number, :area_code
	def initialize( phonenumber )
		#if it contains letters
		if phonenumber =~ /[a-zA-Z]/
			@number = '0000000000'
		else
			phonenumber.gsub!(/[^0-9]/,'')
			if ( phonenumber.length < 10 || phonenumber.length > 11 )
				@number = '0000000000'
			elsif ( phonenumber.length == 11 && phonenumber[0] != '1')
				@number = '0000000000'
			else
				#if it is valid
				if(phonenumber.length == 11)
					phonenumber = phonenumber[1..-1]
				end

				@number = phonenumber
				@area_code = @number[0..2]
			end
		end
	end

	def to_s
		"(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..10]}"
	end

end

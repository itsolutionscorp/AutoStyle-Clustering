class Binary

	def initialize (var)
		@var = var
	end

	def to_decimal
		return 0 if (@var =~ /^[10]+$/).nil?
		sum = 0
		to_digit_array.each_with_index{|num, i|
			sum += num * (2 ** i)
		}
		sum
	end

	private

		def to_digit_array
			@var.each_char.to_a.reverse.map{ |num| num.to_i }
		end
		
end

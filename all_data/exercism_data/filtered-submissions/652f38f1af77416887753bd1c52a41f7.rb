def compute(arg, arg2)
		@dna1 = arg.chars.to_a
		@dna2 = arg2.chars.to_a
		@counter = 0
		@dna1.zip(@dna2).each do |b , c|
			if b != c
				@counter += 1
			end
		end
		return @counter
	end
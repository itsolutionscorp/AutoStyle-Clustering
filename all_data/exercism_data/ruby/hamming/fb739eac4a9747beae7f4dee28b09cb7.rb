class Hamming 

	def initialize dna1, dna2
		@dna1 = dna1
		@dna2 = dna2
	end

	def compute
		
		@dna1.length > @dna2.length ? length = @dna1.length : @dna2.length
		
		arr_dna1 = @dna1.split("")
		arr_dna2 = @dna2.split("")
		
		match = new Fixnum	
		
		length.times do |i|
			match++ if arr_dna1[i] != arr_dna2[i]
		end

		match		
	end

end


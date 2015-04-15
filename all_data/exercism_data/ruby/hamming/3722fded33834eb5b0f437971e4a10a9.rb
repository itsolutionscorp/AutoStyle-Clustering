class Hamming

	def self.compute(a,b)
		counter = 0
		comparison_strand = choose(a,b)

		comparison_strand.each_char.each_with_index do |current_a,index|
			counter = counter+1 if current_a != b[index] 
		end
		
		counter
	end
	
	private 
	def self.choose(a,b)
		a.length <= b.length ? a : b
	end

end

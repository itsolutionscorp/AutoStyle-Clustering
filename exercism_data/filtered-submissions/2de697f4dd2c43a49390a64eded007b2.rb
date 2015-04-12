	class Hamming

		def compute (dna_strand_1,dna_strand_2)
			dna_strand_1.each_char.each_with_index.map { |strand,index| strand!=dna_strand_2[index]}
			.select{|x| x==true}
			.size			
		end
	end
	

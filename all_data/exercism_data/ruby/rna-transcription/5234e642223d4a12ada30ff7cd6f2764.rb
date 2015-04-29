class Complement
	def self.of_dna(dna)
	  rna_complement = ''

      (0..dna.length-1).each do |i|
    	case dna[i]
    	when 'G'
       		rna_complement.concat("C")
   		when 'C'
   	   		rna_complement.concat("G")
   		when 'T'
   			rna_complement.concat("A")
   		when 'A'
   			rna_complement.concat("U")
   		else
   			puts "not valid DNA"
   		end
   		#puts "rna complement #{rna_complement}"

   	   end
   	   rna_complement
	end

	def self.of_rna(rna)
	  dna_complement = ''

      (0..rna.length-1).each do |i|
    	case rna[i]
    	when 'G'
       		dna_complement.concat("C")
   		when 'C'
   	   		dna_complement.concat("G")
   		when 'A'
   			dna_complement.concat("T")
   		when 'U'
   			dna_complement.concat("A")
   		else
   			puts "not valid RNA"
   		end
   		#puts "dna complement #{dna_complement}"

   	   end
   	   dna_complement
	end

end

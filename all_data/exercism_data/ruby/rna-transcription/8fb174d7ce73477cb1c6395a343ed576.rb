class Complement
 def self.of_dna(dna_strand)
	rna_strand=""
 	dna_strand.length.times do |i|
	  	case dna_strand[i]
  			when "G"
  			 rna_strand = rna_strand + "C"
  			when "C"
   			 rna_strand = rna_strand + "G"
  			when "T"
  			 rna_strand = rna_strand + "A"
  			when "A"
  			 rna_strand = rna_strand + "U"
 			end
 		end
 	rna_strand
 end
 def self.of_rna(rna_strand)
	dna_strand=""
 	rna_strand.length.times do |i|
	  	case rna_strand[i]
  			when "G"
  			 dna_strand = dna_strand + "C"
  			when "C"
   			 dna_strand = dna_strand + "G"
  			when "U"
  			 dna_strand = dna_strand + "A"
  			when "A"
  			 dna_strand = dna_strand + "T"
 			end
 		end
 	dna_strand
 end
end

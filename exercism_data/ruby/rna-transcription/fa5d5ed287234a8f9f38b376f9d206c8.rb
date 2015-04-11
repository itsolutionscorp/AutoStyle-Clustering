class Complement
  def self.of_dna(strand)
  	rna_strand = ""
    strand.each_char do |i|
    	case i
    	when "A"
    		rna_strand += "U"
    	when "C"
    		rna_strand += "G"
    	when "G"
    		rna_strand += "C"
    	when "T"
    		rna_strand += "A"
    	else
    		raise ArgumentError
    	end
    end
    	return rna_strand
  end

  def self.of_rna(strand)
    dna_strand = ""
    strand.each_char do |i|
    	case i
    	when "U"
    		dna_strand += "A"
    	when "C"
    		dna_strand += "G"
    	when "G"
    		dna_strand += "C"
    	when "A"
    		dna_strand += "T"
    	else
    		raise ArgumentError
    	end
    end
    	return dna_strand
  end
end

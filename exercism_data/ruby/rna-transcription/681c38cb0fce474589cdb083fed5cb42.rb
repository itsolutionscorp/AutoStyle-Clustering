class Complement

  def self.of_dna(dna)
    @complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    rna_strand = []
    
    dna.each_char do |i|
      rna_strand << @complements[i]
    end

    rna_strand.join
  end
 
  def self.of_rna(rna)
    dna_strand = []
    
    rna.each_char do |i|
      dna_strand << @complements.invert[i]
    end

    dna_strand.join
  end
  
end

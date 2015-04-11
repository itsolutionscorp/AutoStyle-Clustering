class Complement
  SEQUENCES = { G: 'C', 
                C: 'G', 
                T: 'A', 
                A: 'U' }

  def self.of_dna(dna_strand)
    dna_strand.each_char.with_object("") do |dna, rna| 
      rna << SEQUENCES[dna.to_sym] 
    end
  end

  def self.of_rna(rna_strand)
    rna_strand.each_char.with_object("") do |rna, dna| 
      dna << SEQUENCES.key(rna).to_s
    end
  end

end

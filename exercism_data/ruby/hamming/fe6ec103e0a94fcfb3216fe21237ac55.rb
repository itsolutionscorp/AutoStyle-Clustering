class DNA
  def initialize(coding_strand)
    @coding_strand = coding_strand
  end

  def bases
    @coding_strand.chars
  end

  def hamming_distance(mutated_strand)
    tally_mutations(DNA.new(mutated_strand))
  end

  private
    def tally_mutations(mutated_dna)
      pairwise_bases(mutated_dna).count{|o, m| mutation?(o, m)}
    end

    def pairwise_bases(mutated_dna)
      self.bases.zip(mutated_dna.bases)
    end
    
    def mutation?(original_base, new_base)
      new_base && original_base != new_base
    end
end

# Given DNA strand, return RNA complement

class Complement

  def self.of_dna(strand)
    of_strand(strand, @dna_to_rna)
  end

  def self.of_rna(strand)
    of_strand(strand, @rna_to_dna)
  end

  private
  @dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @rna_to_dna = @dna_to_rna.invert

  def self.of_strand(strand, strand_mapper)
    strand.gsub(/[#{strand_mapper.keys.join}]/, strand_mapper)
  end

end

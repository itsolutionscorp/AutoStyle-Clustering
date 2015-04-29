# Given DNA strand, return RNA complement

class Complement

  def self.of_dna(strand)
    complement = of_strand(strand, @dna_to_rna)
  end

  def self.of_rna(strand)
    complement = of_strand(strand, @rna_to_dna)
  end

  private
  @dna_to_rna = {G: :C, C: :G, T: :A, A: :U}
  @rna_to_dna = @dna_to_rna.invert

  def self.of_strand(strand, strand_mapper)
    complement = ''
  
    # Consider each character and map complement
    (strand.length).times do |i|
      complement += strand_mapper[strand[i].to_sym].to_s
    end

    return complement
  end

end

class DNA
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def hamming_distance(mutated)
    dna_chars_zipped_with(mutated).count{ |dna, mut| dna != mut }
  end

  private

  def dna_chars_zipped_with(mutated)    
    strand_length = [@dna_strand.length, mutated.length].min

    dna_chars = @dna_strand.chars.take(strand_length)
    mutated_chars = mutated.chars.take(strand_length)

    dna_chars.zip mutated_chars
  end
end

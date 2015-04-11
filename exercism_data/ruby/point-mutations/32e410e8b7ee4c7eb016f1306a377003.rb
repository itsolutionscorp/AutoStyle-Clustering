class DNA
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def hamming_distance(mutated)
    hamming_distance = 0
    strand_length = [@dna_strand.length, mutated.length].min
    @dna_strand.chars.each_with_index do |nucleotide, i|
      break if i == strand_length
      hamming_distance += 1 unless nucleotide == mutated[i]
    end

    hamming_distance
  end
end

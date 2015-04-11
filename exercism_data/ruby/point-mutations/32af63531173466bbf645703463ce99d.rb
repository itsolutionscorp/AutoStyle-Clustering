class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(test_strand)
    nucleotides = normalize_to_length_of(test_strand).chars
    nucleotides.each_with_index.inject(0) do |mutations, (nucleotide, index)|
      mutations += 1 if nucleotide != test_strand[index]
      mutations
    end
  end

  private

  def normalize_to_length_of(test_strand)
    @strand[0...test_strand.length]
  end

end

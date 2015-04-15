class DNA

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def hamming_distance(test_nucleotides)
    nucleotides = normalize_to_length_of(test_nucleotides)
    nucleotides.each_with_index.inject(0) do |mutations, (nucleotide, index)|
      mutations += 1 if nucleotide != test_nucleotides[index]
      mutations
    end
  end

  private

  def normalize_to_length_of(test_nucleotides)
    @nucleotides[0...test_nucleotides.length].split(//)
  end

end

class DNA
  def initialize dna_string
    @dna_string = dna_string
  end

  def count nucleotide
    raise ArgumentError unless nucleotides(true).include? nucleotide

    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    nucleotides.each_with_object(Hash.new(0)) do |nucleotide, tally|
        tally[nucleotide] = @dna_string.count(nucleotide)
    end
  end

  def nucleotides include_uracil=false
    [
      'A', 'T', 'C', 'G',
      *('U' if include_uracil)
    ]
  end
end

class DNA
  NonNucleotideChars = /[^ACGT]/

  def initialize(dna_string)
    raise ArgumentError if dna_string.match NonNucleotideChars
    @dna = dna_string
  end

  def count(nucleotide)
    raise ArgumentError if nucleotide.match NonNucleotideChars
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    if @counts
      @counts
    else
      @counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
      @dna.each_char.with_object(@counts) {|nuc, counts| counts[nuc] += 1}
    end
  end

end

class DNA
  NONNUCLEOTIDE = /[^ACGT]/
  EMPTYCOUNT = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

  def initialize(dna_string)
    raise ArgumentError if dna_string.match NONNUCLEOTIDE
    @dna = dna_string
  end

  def count(nucleotide)
    raise ArgumentError if nucleotide.match NONNUCLEOTIDE
    nucleotide_counts[nucleotide]
  end

  def empty_count
    EMPTYCOUNT.clone
  end

  def nucleotide_counts
    @counts ||= @dna.each_char.with_object(empty_count) { |nuc, counts| counts[nuc] += 1 }
  end

end

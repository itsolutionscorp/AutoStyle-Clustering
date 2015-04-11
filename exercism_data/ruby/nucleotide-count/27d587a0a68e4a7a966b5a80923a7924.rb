class DNA
  Nucleotide = ['A', 'T', 'C', 'G']

  def initialize(dna_string)
    raise ArgumentError if dna_string.match /[^ATCG]/
    @dna = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotide.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna.each_char.with_object(counts) {|nuc, counts| counts[nuc] += 1}
  end

end

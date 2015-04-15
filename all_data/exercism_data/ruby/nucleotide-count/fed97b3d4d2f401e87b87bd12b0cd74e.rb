class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide =~ /[atcgu]/i
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna_string.chars.each {|nucleotide| counts[nucleotide] += 1 }
    counts
  end
end

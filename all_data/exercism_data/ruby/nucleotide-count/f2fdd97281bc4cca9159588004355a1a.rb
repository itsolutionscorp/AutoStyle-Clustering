class DNA
  def initialize(strand)
    @strand = strand.upcase

    @strand.each_char do |nucleotide|
      raise ArgumentError unless nucleotide.match(dna_matcher)
    end
  end

  def count(nucleotide)
    nucleotide.upcase!
    raise ArgumentError unless nucleotide.match(nucleotide_matcher)

    @strand.chars.count { |nt| nt == nucleotide }
  end

  def nucleotide_counts
    count = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @strand.each_char { |nucleotide| count[nucleotide] += 1 }
    count
  end

  private

  def dna_matcher
    /[ACGT]/
  end

  def nucleotide_matcher
    /[ACGUT]/
  end
end

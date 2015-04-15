class Nucleotide
  def initialize(dna_str)
    @dna_strand = dna_str
  end

  def self.from_dna(dna_str)
    fail ArgumentError unless dna_str.scan(/[^ATCG]/).empty?

    Nucleotide.new(dna_str)
  end

  def count(letter)
    @dna_strand.count(letter)
  end

  def histogram
    @dna_strand.each_char.each_with_object('A' => 0, 'T' => 0, 'C' => 0, 'G' => 0) { |e, a| a[e] += 1 }
  end
end

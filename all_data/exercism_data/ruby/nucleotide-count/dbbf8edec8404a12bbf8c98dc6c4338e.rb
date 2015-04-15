module Enumerable
  def frequencies
    self.each_with_object(Hash.new(0)) do |i, h|
      h[i] += 1
    end
  end
end

class DNA
  NUCLEOTIDES = %w(A T C G)

  def initialize(dna_string)
    raise ArgumentError unless valid_dna_string?(dna_string)

    @dna_string = dna_string
  end

  def nucleotide_counts
    default_counts.merge(@dna_string.chars.frequencies)
  end

  def count(marker)
    raise ArgumentError unless NUCLEOTIDES.include?(marker)
    nucleotide_counts[marker]
  end

  private

  def default_counts
    NUCLEOTIDES.each_with_object({}) { |i, h| h[i] = 0 }
  end

  def valid_dna_string?(str)
    (str.chars - NUCLEOTIDES).empty?
  end
end

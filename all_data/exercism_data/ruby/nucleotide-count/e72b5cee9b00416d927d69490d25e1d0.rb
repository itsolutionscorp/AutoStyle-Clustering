class Nucleotide
  def initialize(strand)
    raise ArgumentError if strand =~ /[^ATCG]/
    @strand = strand
  end

  def count(letter)
    @strand.count(letter)
  end

  def histogram
    base = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @strand.chars.reduce(base) do |histogram, letter|
      histogram.update({letter => histogram[letter] + 1})
    end
  end

  def self.from_dna(strand)
    new(strand)
  end
end

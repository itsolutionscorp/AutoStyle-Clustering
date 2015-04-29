class Nucleotide
  def self.from_dna dna
    new dna
  end

  def initialize dna
    raise ArgumentError if /[^ATCG]/ =~ dna
    @dna = dna
  end

  def count nucleotide
    @dna.count nucleotide
  end

  def histogram
    Hash[['A', 'T', 'C', 'G'].map { |n| [n, (count n)] }]
  end
end

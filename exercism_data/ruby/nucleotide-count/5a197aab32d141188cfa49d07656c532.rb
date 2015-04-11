class Nucleotide
  def self.from_dna str
    new str
  end

  def initialize str
    raise ArgumentError, 'invalid DNA string' if str.match(/[^ATCG]/i)
    @dna = str.upcase
  end

  def count s
    histogram[s]
  end

  def histogram
    @result ||=
      %w[A T C G].each_with_object({}) { |n, r| r[n] = @dna.count(n) }
  end
end

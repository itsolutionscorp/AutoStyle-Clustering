class Nucleotide < String
  def self.from_dna str
    str.match(/[^ACGT]/) ? fail(ArgumentError) : new(str)
  end

  def histogram
    @result ||=
      %w[A T C G].each_with_object({}) { |n, r| r[n] = count(n) }
  end
end

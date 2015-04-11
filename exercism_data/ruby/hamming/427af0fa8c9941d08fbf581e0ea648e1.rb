class Hamming
  def self.compute(strand_a, strand_b)
    return 0 if strand_a == strand_b
    @strand_a = strand_a.split("")
    @strand_b = strand_b.split("")
    point_mutations
  end

  private
  def self.point_mutations
    i = 0
    hamming = 0
    while i < Hamming.strand_length
      hamming += 1 unless @strand_a[i] == @strand_b[i]
      i += 1
    end
  end

  def self.strand_length
    [@strand_a.length, @strand_b.length].sort!.first
  end
end

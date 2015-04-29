require 'pry'

class Hamming
  def self.compute(strand_a, strand_b)
    return 0 if strand_a == strand_b
    a = strand_a.split("")
    b = strand_b.split("")
    hamming = 0
    i = 0
    while i < Hamming.strand_length(a,b)
      hamming  += 1 unless a[i] == b[i]
      i += 1
    end
    hamming
  end

  private

  def self.strand_length(strand_a, strand_b)
    [strand_a.length, strand_b.length].sort!.first
  end
end

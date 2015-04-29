module Hamming
  def compute(strand_a, strand_b)
    [strand_a.size, strand_b.size].min.times.collect do |n|
      strand_a[n] != strand_b[n]
    end.count(true)
  end
end
Hamming.extend(Hamming)

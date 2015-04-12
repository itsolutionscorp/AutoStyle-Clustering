module Hamming

  def compute(strand_1, strand_2)
  [strand_1.length, strand_2.length].
    min.
    times.
    count { |i| strand_1[i] != strand_2[i] }
  end

end

module Hamming

  def compute(strand1, strand2)
    count = 0
    shortest_length = [strand1.length, strand2.length].min
    0.upto(shortest_length - 1) do |i|
      count += 1 if strand1[i] != strand2[i]
    end
    count
  end

end

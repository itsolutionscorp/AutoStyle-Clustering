module Hamming
  def compute(strand1, strand2)
    min = [strand1.length, strand2.length].min
    (0...min).count { |base| strand1[base] != strand2[base] }
  end
end

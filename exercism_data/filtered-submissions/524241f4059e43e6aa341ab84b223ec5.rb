module Hamming
  def compute(strand1, strand2)
    shortest_strand = [strand1.size, strand2.size].min

    (0...shortest_strand).count do |point|
      strand1[point] != strand2[point]
    end
  end
end

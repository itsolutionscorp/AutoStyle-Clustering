module Hamming
  def self.compute(strand1, strand2)
    shortest_strand = [strand1.size, strand2.size].min

    (0...shortest_strand).reduce(0) do |ham, point|
      ham + (strand1[point] <=> strand2[point]).abs
    end
  end
end

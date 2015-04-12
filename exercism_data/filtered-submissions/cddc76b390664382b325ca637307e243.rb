def compute(strand1, strand2)
    shortest_strand = [strand1, strand2].min
    (0...shortest_strand.length).count { |num| strand1[num] != strand2[num] }
  end
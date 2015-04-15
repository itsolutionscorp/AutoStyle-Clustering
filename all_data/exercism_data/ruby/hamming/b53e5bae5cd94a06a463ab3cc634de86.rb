class Hamming
  def self.compute(strand_one, strand_two)
    shortest_length = [strand_one.size, strand_two.size].min

    shortest_length.times.count { |i| strand_one[i] != strand_two[i] }
  end
end

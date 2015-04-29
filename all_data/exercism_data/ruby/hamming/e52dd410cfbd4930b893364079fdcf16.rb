class Hamming < Struct.new(:strand_a, :strand_b)

  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).compute
  end

  def compute
    positions_with_differences.count
  end

private

  def positions_with_differences
    common_positions.select { |pos| strand_a[pos] != strand_b[pos] }
  end

  def common_positions
    shortest_strand_length.times
  end

  def shortest_strand_length
    [strand_a.size, strand_b.size].min
  end

  #def compute(strand_a, strand_b)
  #  strand_length = [strand_a.size, strand_b.size].min
  #  strand_length.times.select { |i| strand_a[i] != strand_b[i] }.count
  #end

end

class Hamming

  def self.compute(a, b)
    shortest_length(a,b).times.count { |i| a[i] != b[i] }
  end

  private

  def self.shortest_length(a,b)
    [a.size, b.size].min
  end

end

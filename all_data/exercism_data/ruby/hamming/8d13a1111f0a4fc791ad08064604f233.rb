class Hamming
  def self.compute(a, b)

    shortest_length = [a.to_s.length, b.to_s.length].min

    (0...shortest_length).count { |i| a[i] != b[i] }

  end
end

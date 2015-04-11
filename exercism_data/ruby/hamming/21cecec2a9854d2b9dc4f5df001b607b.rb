class Hamming
  def self.compute a, b
    length = [a.size, b.size].min

    (0...length).select do |i|
      a[i] != b[i]
    end.size
  end
end

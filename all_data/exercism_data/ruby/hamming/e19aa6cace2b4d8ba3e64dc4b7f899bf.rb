class Hamming
  def self.compute(a, b)
    length = [a.length, b.length].min
    (0...length).count { |index| a[index] != b[index] }
  end
end

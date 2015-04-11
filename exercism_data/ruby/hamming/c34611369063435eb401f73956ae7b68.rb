class Hamming

  def self.compute(a, b)
    diff = 0
    length = [a.length, b.length].min
    (0..length-1).each { |i| diff += 1 if a[i] != b[i] }
    diff
  end

end

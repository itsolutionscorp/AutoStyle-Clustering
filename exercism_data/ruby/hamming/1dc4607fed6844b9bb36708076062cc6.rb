class Hamming

  def self.compute(a, b)
    0.upto(a.length - 1).select do |i|
      a[i] != b[i]
    end.count
  end

end

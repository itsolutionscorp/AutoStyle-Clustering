class Hamming
  def self.compute(a, b)
    res = 0
    [a.length, b.length].min.times do |i|
      if a[i] != b[i]
        res += 1
      end
    end
    return res
  end
end

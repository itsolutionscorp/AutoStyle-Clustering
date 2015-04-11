class Hamming
  def self.compute(a, b)
    longest(a, b).times.count do |idx|
      a[idx] != b[idx]
    end
  end

  def self.longest(a, b)
    [a.length, b.length].max
  end
end

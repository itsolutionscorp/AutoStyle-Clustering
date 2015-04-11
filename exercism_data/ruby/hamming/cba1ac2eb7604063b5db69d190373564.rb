class Hamming
  def self.compute(a,b)
    distance = 0
    for i in 0..([a.length, b.length].min - 1)
        distance += 1 if a[i] != b[i]
    end
    distance
  end
end

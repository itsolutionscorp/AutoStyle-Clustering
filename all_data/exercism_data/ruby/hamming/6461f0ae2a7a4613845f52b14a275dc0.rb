class Hamming
  def self.compute(a,b)
    count = 0
    i = 0
    while i < a.length
      count += 1 if a[i] != b[i]
      i += 1
    end
    count

  end
end

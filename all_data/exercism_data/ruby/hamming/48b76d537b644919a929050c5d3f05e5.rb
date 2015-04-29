class Hamming
  def self.compute(a, b)
    hamming = 0

    for i in 0...a.length
      hamming +=1 if a[i] != b[i]
    end

    return hamming
  end
end

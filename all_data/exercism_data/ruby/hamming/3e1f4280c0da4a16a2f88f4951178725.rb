class Hamming

  def self.compute(a,b)
    return 0 if a.length != b.length
    distance = 0
    a.length.times do |i|
      distance += 1 if a[i] != b[i]
    end
    distance
  end

end

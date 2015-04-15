class Hamming
  def self.compute(a, b)
    min_length = a.length > b.length ? b.length : a.length
    hamming = 0
    min_length.times do |i|
      hamming +=1 if a[i] != b[i]
    end
    hamming
  end
end

class Hamming
  def self.compute(a, b)
    min_length = [a.size, b.size].min
    differences(a, b, min_length)
  end

  def self.differences(a, b, min_length) 
    count = 0
    min_length.times do |i|
      count += 1 if a[i] != b[i]
    end
    count
  end
end

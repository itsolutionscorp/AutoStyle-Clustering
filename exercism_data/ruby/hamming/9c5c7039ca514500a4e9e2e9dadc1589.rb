class Hamming
  def self.compute(a, b)
    count = 0
    range = 0..a.length-1
    range.each do |i|
      count += 1 if a[i] != b[i]
      i += 1
    end
    count
  end
end

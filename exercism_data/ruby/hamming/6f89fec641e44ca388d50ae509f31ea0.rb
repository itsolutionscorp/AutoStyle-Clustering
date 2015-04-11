class Hamming
  def self.compute a, b 
    sum = 0;
    limit = a.length < b.length ? a.length : b.length
    (0...limit).each do |i|
      sum += 1 if a[i] != b[i]
    end
    sum
  end
end

class Hamming
  def self.compute(a,b)
    dist = 0  
    len = [a.length, b.length].min
    (0..len-1).each { |i| dist += 1 if a[i] != b[i] }
    dist
  end
end

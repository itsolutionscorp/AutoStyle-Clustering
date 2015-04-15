class Hamming
  def self.compute(a, b)
    [a,b].map(&:size).min.times.count{|i| a[i] != b[i]} 
  end
end

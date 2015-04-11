class Hamming
  
  def self.compute(a,b)
    pairs = a.split('').zip b.split('')
    pairs.select {| a, b| a != b}.size
  end
  
end

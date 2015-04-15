class Hamming
  
  def self.compare(orig, copy)
    orig==copy ? 0 : 1  
  end

  def self.compute(orig, copy)
    (0..orig.length-1).inject(0) { |acc, i| acc + self.compare(orig[i], copy[i]) }
  end

end

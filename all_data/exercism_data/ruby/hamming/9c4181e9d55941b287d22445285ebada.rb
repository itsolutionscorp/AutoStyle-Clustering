class Hamming
  def self.compute(a,b)
    return -1 if a.size != b.size
    
    diff = 0
    
    # Check char-by-char difference.
    a.size.times do |i|
      diff += 1 if a[i] != b[i]
    end
    
    diff
  end
end

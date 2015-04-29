class Hamming
  
  def self.compute(original, copy)
    score = 0
    min_length(original, copy).times do |i|
      score += 1 if original[i] != copy[i]
    end
    score
  end
  
  private 
  
  def self.min_length(original, copy)
    [original.length, copy.length].min
  end
  
end

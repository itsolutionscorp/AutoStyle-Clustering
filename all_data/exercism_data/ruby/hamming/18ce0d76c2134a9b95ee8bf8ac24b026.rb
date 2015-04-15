class Hamming
  attr_reader :original, :copy
  
  def initialize(original, copy)
    @original = original
    @copy = copy
  end
  
  def self.compute(original, copy)
    new(original, copy).distance
  end
  
  def distance
    score = 0
    common_length(original, copy).times do |i|
      score += 1 if mutation?(original[i], copy[i])
    end
    score
  end
    
  private 
  
  def common_length(original, copy)
    [original.length, copy.length].min
  end
  
  def mutation?(a, b)
    a != b
  end
  
end

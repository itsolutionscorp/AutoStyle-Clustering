class Hamming
  def self.compute(first, second)
    differences = 0
    
    if first == second
      return differences
    end
    
    first = first.scan /\w/
    second = second.scan /\w/
    
    first.each_with_index do |value, index|
      
      if index + 1 > second.length 
        return differences
      end
      
      unless first[index] == second[index]
        differences += 1
      end
    end
    
    differences
  end
end

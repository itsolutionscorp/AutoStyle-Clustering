class Hamming
  attr_reader :a, :b

  def self.compute(a, b)
    if a == b
      0
    else
    compare_string(a,b)
    end
  end

  def self.compare_string(string1, string2)
    string1 = string1.split('')
    string2 = string2.split('')
    counter = 0
    string1.zip(string2).map do |x,y| 
      unless x == nil || y == nil
      counter += 1 if x != y
      end
    end
    return counter
  end
  
end

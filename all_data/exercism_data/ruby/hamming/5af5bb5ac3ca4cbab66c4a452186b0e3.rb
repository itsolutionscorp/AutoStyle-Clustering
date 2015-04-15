class Hamming
  
  def self.compute(string1, string2)  
    if string1.equal?(string2)
      return 0
    end
    
    @array1 = string1.scan(/[AGTC]/)
    @array2 = string2.scan(/[AGTC]/)
    
    distance
  end
  
  private
  
  def self.distance
    d = 0
    
    min_length.times do |i|
      if @array1[i] != @array2[i]
	d = d + 1
      end
    end
    
    return d
  end
  
  def self.min_length
    if @array1.length <= @array2.length
      return @array1.length
    else
      return @array2.length
    end
  end
  
end

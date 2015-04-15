class Series
 attr_reader :input, :bucket 
  
  def initialize s
    @input = s.split('').map { |s| s.to_i }
    @bucket = []
  end
  
  def slices n 
    raise(ArgumentError) if n > input.length
    
    i = 0  
    while i + n <= input.length
     bucket << input[i...i+n] 
     i+=1
    end
    @bucket
  end
end

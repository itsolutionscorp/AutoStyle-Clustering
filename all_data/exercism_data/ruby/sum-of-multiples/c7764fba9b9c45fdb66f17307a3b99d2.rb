class SumOfMultiples

  attr_reader :multiples

  def initialize(*args)
     @multiples = [*args]
  end
  
  def self.to(range)
    if @multiples.nil?
      multiples_arr = get_multiples([3,5],range)
      get_sum_of_multiples(multiples_arr.uniq)
     end
  end
  
  def to(range)
       factors_arr = self.class.get_multiples(@multiples,range)
       self.class.get_sum_of_multiples(factors_arr.uniq)
  end 
  
  
  private
    
    def self.get_multiples(arr, range)
     multiples_arr = []
      arr.each do |i|
        (0...range).each do |j|
            if j%i == 0 
              multiples_arr << j
            end
        end
      end  
      multiples_arr
    end
    
    def self.get_sum_of_multiples(multiples_arr)
      sum_of_mul = 0
      multiples_arr.each do |m|
        sum_of_mul += m
      end
      sum_of_mul
    end
    
    

end

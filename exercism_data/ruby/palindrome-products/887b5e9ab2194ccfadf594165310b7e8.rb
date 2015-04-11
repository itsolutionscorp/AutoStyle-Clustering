class Palindrome
  
  attr_reader :value, :factors
  
  def initialize
    @factors = []
  end  
  
  def value=(v)
    @value = v
  end
    
end  
class Palindromes
  
  attr_accessor :factors
  
  def initialize(max_factor: 1,min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
    @factors = {}
  end
  
  def generate
    @max_factor.downto(@min_factor) do |i|
      (i).downto(@min_factor) do |j|
        n = i * j
        if n.to_s == n.to_s.reverse then
          p = @factors.fetch(n,Palindrome.new)
          p.value= n
          p.factors.push([j,i])
          @factors[n] = p
        end  
      end  
    end  
  end
  
  def largest
    @factors[@factors.keys.max]
  end 
  
  def smallest
    @factors[@factors.keys.min]
  end     
  
end  

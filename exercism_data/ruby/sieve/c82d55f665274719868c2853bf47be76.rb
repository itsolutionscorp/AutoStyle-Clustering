class Sieve
  attr_reader :range
  
  def initialize(range)
    @range = range
  end
  
  def primes
  prime_arr = []
 #  p "Range",@range
   if @range >= 2
    (2..@range).each do |i|
       c= 0
       (i).downto(1) do |j|
         if i%j == 0
          c += 1
        end 
       end       
       if c==2
        prime_arr << i
       end
    end
   #  p prime_arr
    prime_arr
    end
  end
   
end

#s = Sieve.new(2)
#p s.primes
#s1 = Sieve.new(1).primes
#s2 = Sieve.new(4).primes

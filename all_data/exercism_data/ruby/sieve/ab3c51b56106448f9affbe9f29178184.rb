class Sieve
  def initialize(n)
    @n = n
  end
  
  def primes
    nums = (0..@n).to_a
    nums[1]=0
    prim = []
    
    nums.each do |n|
      next if n==0
      
      prim << n
      new = n*2
      while new <= @n
        nums[new] = 0
        new += n
      end
    end
    
    return prim
  end
end

class PrimeFactors  
  def self.for(n)
    factors = []
    p=2
    while n > 1
      if n % p == 0
	n /= p
	factors << p
      else
	p+=1
      end
    end      
    factors
  end
end

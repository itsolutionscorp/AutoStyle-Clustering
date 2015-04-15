class PrimeFactors

  def self.for(n)
    pf = []
    i = 2
    loop do
      if n%i == 0
        n = n/i 
        pf << i
      else
        i=i+1
      end    
      break if n == 1
    end
    pf
  end
     
end
  
  

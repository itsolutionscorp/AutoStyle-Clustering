class Sieve
  
  def initialize(n)
    @range =*(2..n)
    i=2
    loop do
      remove_marked(i)
      i+=1
    break if i==n
    end  
  end  
  
  def primes
    @range  
  end 
  
  private
  def remove_marked(n)
    m=n+n 
    while m <= @range.last do
      @range.delete(m)
      m=m+n 
    end  
  end  
   
end  

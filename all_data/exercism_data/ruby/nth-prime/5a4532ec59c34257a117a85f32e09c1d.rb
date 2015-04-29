class Prime
  def self.nth(n)
    raise ArgumentError if n<1
    return 2 if n==1
    
    i=3
    pos = 2
    
    while pos < n
      i += 2
      pos += 1 if is_prime(i)
    end
    return i
  end
  
  private
  def self.is_prime(n)
  
    return true if n == 2
    return false if n < 2
    return false if n%2 == 0
    
    limit = Math.sqrt(n).ceil
    
    (3..limit).step(2) {|x| return false if n%x == 0}
    return true
  end
end

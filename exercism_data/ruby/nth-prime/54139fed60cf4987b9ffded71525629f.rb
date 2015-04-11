class Fixnum
  
  def is_prime?
    i = 2
    return false if self < 2
    while i < self do
      return false if self % i == 0
      i += 1
    end
    true
  end
  
end

class Prime
  
  def self.nth(n)
    raise ArgumentError if n == 0
    np = 0
    p = 1
    while np < n do
      p += 1
      np += 1 if p.is_prime?
    end
    p
  end
  
end

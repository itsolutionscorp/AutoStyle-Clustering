class Prime < Numeric

  def self.is_prime?(n)
    if n % 2 == 0
      return (n == 2)
    else
      for x in 3..(Math.sqrt(n).ceil)
        if n % x == 0
          return false 
        end
      end
    end
    return true
  end 

  def self.nth(ord)
    if ord < 1 then raise ArgumentError
    else
      i, x = 0, 2
      while i < ord
        self.is_prime?(x) ? i += 1 : nil
        x += 1
      end
    end
    x - 1
  end
  
end

class Prime
  def nth(n)
    raise ArgumentError if n <= 0
    
    i = 1
    while n > 0
      i = i + 1
      if i.is_prime?
        n = n - 1
      end
    end

    i
  end

end

class Integer
  def is_prime?
    half = self / 2
    i = 2
    
    while i <= half
      return false if self % i == 0
      i = i + 1
    end

    return true
  end
end

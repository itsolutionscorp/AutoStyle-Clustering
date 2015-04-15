module Prime

  def self.nth(num)
    counter, iterator = 0, 0
    until counter == num
      iterator += 1
      counter  += 1 if isPrime(iterator)
    end
    iterator
  end

  def self.isPrime(n)
    return false if n < 2
    # check if n cannot be evenly divisible with any number down to 2
    (n-1).downto(2).none? { |i| n % i == 0 }
  end

end

class Prime

  def self.nth(num)
    raise ArgumentError if num < 1

    count = 1
    increment = 2

    until count == num
      increment += 1
      until is_prime?(increment)
        increment += 1 
      end
      count += 1
    end

    increment
  end

  def self.is_prime?(num)
    return false if num <= 1 || num % 2 == 0
    
    divisor = 3
    while divisor <= Math.sqrt(num)
      return false if num % divisor == 0
      divisor += 2
    end

    true
  end
end

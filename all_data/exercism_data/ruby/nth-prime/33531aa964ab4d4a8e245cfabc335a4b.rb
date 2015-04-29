class Prime
  def self.is_prime(num)
    return true if num < 4
    return false if num % 2 == 0 || num % 3 == 0
    i = 3
    while i < num ** 0.5 + 1
      return false if num % i == 0
      i += 2
    end
    return true
  end
  def self.nth(num)
    raise ArgumentError if num < 1
    result = 0
    numbers = 0
    current = 2
    while numbers < num
      if is_prime(current)
        result = current
        numbers += 1
      end
      current % 2 == 0 ? current += 1 : current += 2
    end
    return result
  end
end

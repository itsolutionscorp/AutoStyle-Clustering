class Prime

  def self.is_prime(num)
    (2..(Math.sqrt(num))).each do |i|
      return false if num % i == 0
    end
    return true
  end

  def self.nth(n)
  	raise ArgumentError if n <= 0
  	return 2 if n == 1
    count = 3
    index = 1
    while true
      index += 1 if is_prime(count)
      return count if index == n
      count += 2
    end
  end

end

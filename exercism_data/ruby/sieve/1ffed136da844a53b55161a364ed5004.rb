class Sieve
  def initialize(num)
	@num = num
  end
  def is_prime(num)
    return true if num < 4
    return false if num % 2 == 0 || num % 3 == 0
    (3..num ** 0.5 + 1).step(2).each {|i| return false if num % i == 0}
    return true
  end
  def primes
  	result = []
  	(2..@num).each {|n| result << n if is_prime(n)}
  	return result
  end
end

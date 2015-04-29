class Sieve
  
  def initialize(limit)
  	@limit = limit
  end

  def primes
  	primes = []
  	(2..@limit).each {|x| primes << x if is_prime?(x)}
    primes
  end
  
  def is_prime?(x)
  	(2..x/2).each {|y| return false if x % y == 0}
  	true
  end

end

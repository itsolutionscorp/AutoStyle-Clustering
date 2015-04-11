module Prime
  extend self

  def nth(n)
    raise ArgumentError, 'Argument must be greater than 1' if n < 1
    primes = 0 
    i = 1
    loop do
      return i if primes == n
      i += 1
      primes += 1 if isPrime(i)
    end
    primes
  end

  def isPrime(n)
    (2..Math.sqrt(n)).each do |i|
      return false if n%i == 0 
    end
    true
  end
end

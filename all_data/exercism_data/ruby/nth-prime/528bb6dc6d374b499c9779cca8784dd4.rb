class Prime
  def self.nth(i)
  	fail ArgumentError if (i < 1)
    (Prime.first i).last
  end

  :private

  def self.first(i)
  	primes = [2]
  	candidate = 3
  	while primes.length < i
  	  primes << candidate if Prime.sieve(candidate, primes)
  	  candidate += 2
  	end
  	return primes
  end

  def self.sieve(candidate, primes)
  	primes.each do |p|
  	  return true  if p > Math.sqrt(candidate)
  	  return false if (candidate % p) == 0
  	end
  	true
  end
end

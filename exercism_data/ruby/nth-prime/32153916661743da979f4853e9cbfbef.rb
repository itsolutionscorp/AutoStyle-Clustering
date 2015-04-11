class Prime
  @@primes = [2, 3]

  def self.nth(i)
  	fail ArgumentError if (i < 1)
    (Prime.first i).last
  end

  :private

  def self.first(i)
  	candidate = @@primes.last
  	while @@primes.length < i
  	  candidate += 2
  	  @@primes << candidate if Prime.test(candidate)
  	end
  	return @@primes.slice(0 .. i - 1)
  end

  def self.test(candidate)
  	@@primes.each do |p|
  	  return true  if p > Math.sqrt(candidate)
  	  return false if (candidate % p) == 0
  	end
  	true
  end
end

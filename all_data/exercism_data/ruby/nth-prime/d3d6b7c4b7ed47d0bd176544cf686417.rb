# Find the nth prime

class Prime
  # sort of persistent list of primes
  # We'll cheat and "prime" the pump a little
  @@primes=[2]
  
  def self.nth (n)
    # Poor parameter checking
    raise ArgumentError, "There is no zeroth prime" if n==0
    raise ArgumentError, "Argument is not numeric" unless n.is_a? Numeric
    # Simplest case, we already have calculated the prime
    return @@primes[n-1] if @@primes[n-1]

    # It looks like we must find all primes up to the target prime.

    # Our first non-prime
    candidate=3

    while @@primes.length <= n-1
      prime_flag=1

      @@primes.each do |prime|
        if candidate % prime==0
          prime_flag=0
          # We don't need to stay in the loop if we have found a factor
          break
        end
      end
      if prime_flag==1
        @@primes.push(candidate)
      end
      candidate+=1
    end
    @@primes[n-1]

  end

end

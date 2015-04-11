class Sieve
  def initialize(max)
    @max = max
  end

  def primes
    @primes ||= eratosthenes_opt
  end

  private 

  def eratosthenes
    sieve = (0..@max).to_a 
    sieve[0] = sieve[1] = nil

    # when we reach the sqrt of the max then the list
    # will be completely filtered of non primes
    filter_point = Math.sqrt(@max).to_i + 1

    i = 0
    while i < filter_point
      i += 1 until sieve[i]
      (i**2..@max).step(i).each {|n| sieve[n] = nil }
      i += 1
    end

    sieve.compact!
  end

  # optimised by treating even numbers specially
  # ~ 2 x faster
  def eratosthenes_opt
    # compact sieve by creating it without even numbers
    sieve = (3..@max).step(2).to_a 

    # when we reach the sqrt of the max then the list
    # will be completely filtered of non primes
    filter_point = ((Math.sqrt(@max) - 3)/2).to_i + 1

    i = 0
    while i < filter_point
      i += 1 until sieve[i]
      p = sieve[i]
      (p**2..@max).step(p*2).each {|n| sieve[(n-3)/2] = nil }
      i += 1
    end

    sieve.compact!
    sieve.unshift(2)
  end
end

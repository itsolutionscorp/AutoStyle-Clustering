##
# Converts numbers to raindrop-speak.

class Raindrops

  ##
  # Convert the given number to raindrop-speak.

  def self.convert(number)

    factors = prime_factors(number)

    three = factors.include?(3)
    five = factors.include?(5)
    seven = factors.include?(7)

    output = ''
    output += 'Pling' if three
    output += 'Plang' if five
    output += 'Plong' if seven
    if !three && !five && !seven
      output += number.to_s
    end

    output

  end

  ##
  # Determine prime factors of the given integer.
  def self.prime_factors(n)
    return [1] if n == 1

    primes = prime_sieve(n**0.5 + 1)
    factors = []
    primes.each { |prime|
      break if prime ** 2 > n
      while n % prime == 0
        factors << prime
        n /= prime
      end
    }
    if n > 1
      factors << n
    end
    factors
  end

  ##
  # Calculate primes up to n using the Sieve of Eratosthenes
  # See: http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

  def self.prime_sieve(n)
    primes = Array.new(n, true)
    (2..Math.sqrt(n)).each do |i|
      if primes[i]
        c = 0
        while true
          j = i**2 + c*i
          break if j > n
          primes[j] = false
          c += 1
        end
      end
    end
    result = []
    (2..n).each do |index|
      result << index if primes[index]
    end
    result
  end

end

class Prime

  # Non-recursive solution
  def self.nth(n)
    raise ArgumentError if n < 1
    primes, n, i = [], n, 2

    while primes.length != n
      primes << i if is_prime?(i)
      i += 1
    end
    primes[-1]
  end

  # # Recursive soulution (Stack level too deep, comments welcomed!)
  # def self.nth(n)
  #   raise ArgumentError if n < 1
  #   @primes, @n = [], n

  #   recursive(2)
  # end

  # def self.recursive(i)
  #   return @primes[-1] if @primes.length == @n
  #   @primes << i if is_prime?(i)
  #   recursive(i+1)
  # end

  private
  def self.is_prime?(n)
    !(2..Math.sqrt(n)).to_a.any? { |d| n%d == 0 }
  end

end

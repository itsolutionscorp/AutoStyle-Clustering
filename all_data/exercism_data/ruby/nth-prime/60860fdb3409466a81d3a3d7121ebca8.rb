class Prime
  def self.nth(n)
    raise ArgumentError if n == 0

    @known_primes = [2]
    number = 3

    while @known_primes.count < n
      @known_primes << number if is_prime?(number)
      number += 2
    end

    @known_primes.last
  end

  def self.is_prime?(number)
    @known_primes.take_while{ |prime| prime <= Math.sqrt(number) }.each do |prime|
      return false if number % prime == 0
    end

    true
  end
end

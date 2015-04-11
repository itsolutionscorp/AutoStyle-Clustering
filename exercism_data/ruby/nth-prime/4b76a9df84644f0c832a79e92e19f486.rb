module Prime
  def self.nth(n)
    raise ArgumentError.new if n <= 0
    return 2 if n == 1
    primes = [2, 3]
    i = primes[-1]
    while primes.size < n
      primes << i unless divisedByArray(i, primes)
      i += 2
    end
    primes[-1]
  end

  private

  def self.divisedByArray(number, array)
    array.map do |divisor|
      true if number % divisor == 0
    end.compact.size > 0
  end

  def self.is_prime?(number)
    range = (2 .. number/2)
    range.map { |num| num if number % num == 0 }.compact.size == 0
  end
end

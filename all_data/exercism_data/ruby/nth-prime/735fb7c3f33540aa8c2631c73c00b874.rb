class Prime
  attr_reader :number

  def self.nth(number)
    new.nth(number)
  end

  def nth(number)
    raise ArgumentError, "must be greater than zero" unless number > 0

    (1..Float::INFINITY).lazy.select { |integer|
      is_prime?(integer)
    }.take(number).reduce([]) { |primes, prime| primes << prime }.last
  end

  def is_prime?(number)
    square_root = Math.sqrt(number).ceil
    return false if number < 2
    return true if number == 2
    (2..square_root).all? { |i| number % i != 0 }
  end
end

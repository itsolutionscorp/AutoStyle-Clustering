class Prime

  BASE_PRIMES = [2, 3, 5, 7, 11]

  def initialize
    @primes = BASE_PRIMES
  end

  def self.nth(number)
    raise ArgumentError if number.to_i < 1
    Prime.first(number.to_i).last
  end

  def self.first(number)
    new.first(number)
  end

  def first(number)
    build_n_primes(number)
    @primes.first(number)
  end

  def is_prime?(number)
    max_divisor_to_calc = Math.sqrt(number).ceil
    @primes.each do |divisor|
      break if divisor > max_divisor_to_calc
      return false if number % divisor == 0
    end
    true
  end

  private

  def build_n_primes(number)
    prime_candidate = @primes.last + 2
    until number <= @primes.length do
      @primes << prime_candidate if is_prime?(prime_candidate)
      prime_candidate += 2
    end
  end

end

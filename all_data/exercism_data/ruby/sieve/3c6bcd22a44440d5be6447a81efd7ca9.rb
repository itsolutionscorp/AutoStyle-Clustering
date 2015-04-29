class Sieve

  def initialize(num)
    @num = num
  end

  def primes
    primes_collection = [2]
    (3..@num).each do |n|
      if is_prime?(n)
        # p n
        primes_collection << n
      end
    end
    primes_collection
  end

  private

  def is_prime?(num)
    divisors = (2..num-1)
    divisors.each do |i|
      if num % i == 0
        return false
      end
    end
    return true
  end

end

require 'pry'
class Prime

  def self.primes(num)
  prime_nums = [2]
    while prime_nums.length < num
      prime_nums << find_next_prime(prime_nums.last)
    end
    prime_nums
  end

  def self.find_next_prime(last_prime)
    test_num = last_prime + 1
    until prime?(test_num) do
      test_num += 1
    end
    test_num
  end

  def self.nth(n)
    if n < 1 || n.class != Fixnum
      raise ArgumentError
    else
    primes(n)[n-1]
    end
  end

  def self.prime?(n)
    check = (2..Math.sqrt(n).to_i).select {|number| n % number != 0}
    check.count == (2..Math.sqrt(n)).count
  end
end

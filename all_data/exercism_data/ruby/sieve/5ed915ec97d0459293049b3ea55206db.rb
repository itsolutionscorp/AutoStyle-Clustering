require "byebug"
class Sieve
  def initialize(n)
    @n = n
  end

  def primes
    prime_array = [2]
    3.upto(@n) do |x|
      prime_array << x if prime_array.none? {|p| x % p == 0}
    end
    prime_array
  end
end

class Sieve
  def initialize(num)
    @num = num
  end

  def primes
    primes = *(2..@num)

    primes.each do |prime|
      2.upto(@num/prime) do |i|
        primes.delete(prime*i)
      end
    end

  end
end

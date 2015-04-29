class Sieve
  def initialize(max)
    @max = max
    @numbers = (2..@max).to_a
  end

  def primes
    primes = []
    @numbers.each do |num|
      p = num
      while p*num <= @max
        @numbers.delete(p*num)
        p += 1
      end
      primes << num
    end
    primes
  end
end

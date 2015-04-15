class Sieve
  attr_reader :primes

  def initialize(n)
    @primes = []
    range = (2..n).to_a

    while range.any?
      @primes << range.shift
      range.reject! { |n| n % @primes.last == 0 }
    end
  end
end

class Sieve
  def initialize(n)
    @upperbound = n
  end

  def primes
    results = []
    range = (2..@upperbound).to_a

    until range.length <= 0
      number = range.shift
      results << number unless results.any? { |n| number % n == 0 }
    end

    results
  end
end

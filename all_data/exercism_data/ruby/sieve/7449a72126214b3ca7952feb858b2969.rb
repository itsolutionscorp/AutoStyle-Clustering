class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    range = (2..@limit).to_a
    result = []

    until range.empty? do
      result << range.shift
      range.delete_if { |i| (i % result.last).zero? }
    end

    result
  end
end

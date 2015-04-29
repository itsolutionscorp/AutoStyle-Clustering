class Sieve
  attr_reader :limit

  def initialize(limit)
    @limit = limit
  end

  def primes
    Array.new.tap do |result|
      candidates = (2..limit).to_a

      while candidates.any?
        current = candidates.shift
        candidates.delete_if { |n| n % current == 0 }
        result << current
      end
    end
  end
end

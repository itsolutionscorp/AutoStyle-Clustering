class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes
    primes = (2..@limit).to_a

    primes.each do |number|
      if number == nil
        next
      end

      factor = 2
      loop do
        if number * factor > @limit
          break
        end
        primes[(number * factor) - 2] = nil
        factor += 1
      end
    end
    primes.select { |number| number != nil }
  end

end

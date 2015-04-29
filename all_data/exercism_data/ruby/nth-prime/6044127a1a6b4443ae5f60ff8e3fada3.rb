require 'mathn'

class Prime

  PRIMES = self.first(100_000)

  def self.nth(position)
    if position == 0
      raise ArgumentError
    else
      PRIMES[position - 1]
    end
  end
end

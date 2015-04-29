require 'prime'

class Prime

    def self.nth(aNumber)
        primes = self.first aNumber
        raise ArgumentError, 'No 0th prime' unless primes.length > 0
        primes.last
    end

end

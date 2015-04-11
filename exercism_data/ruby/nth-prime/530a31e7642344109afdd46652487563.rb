class Prime
    @@primes = [2]

    def self.nth(n)
        raise ArgumentError, 'must be a positive number' unless n > 0

        if @@primes.length >= n
            return @@primes[n-1]
        end

        get_more_primes(n)

        @@primes.last
    end

    def self.get_more_primes(n)

        m = @@primes.last
        while @@primes.length < n
            m += 1
            not_prime = false
            @@primes.each do |p|
                not_prime = true if m % p == 0
                break if not_prime
            end

            next if not_prime

            @@primes.push m
        end
    end
end

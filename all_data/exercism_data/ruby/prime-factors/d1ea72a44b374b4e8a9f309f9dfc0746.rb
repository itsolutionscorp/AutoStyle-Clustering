module PrimeFactors
    @@primes = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,91,97]

    def self.for(n)
        factors = []
        remainder = n
        prime_index = 0

        while remainder >= 2
            prime = least_prime_factor(remainder)
            factors.push prime
            remainder /= prime
        end
        factors
    end

    private

    def self.factor?(n, prime)
        n >= prime && n % prime == 0
    end

    def self.least_prime_factor(n)
        return n if @@primes.include? n

        greatest_candidate = Math.sqrt(n).to_i
        get_primes_up_to greatest_candidate
        prime = find_prime_up_to(n, greatest_candidate)
        if prime.nil?
            @@primes.push n
            prime = n
        end
        prime
    end

    def self.find_prime_up_to(n, prime_limit)
        prime_index = 0
        prime = @@primes[prime_index]
        while prime <= prime_limit
            return prime if n % prime == 0
            prime_index += 1
            prime = @@primes[prime_index]
        end
    end

    def self.get_primes_up_to(n)
        candidate = @@primes.last + 2
        len = @@primes.length
        while candidate <= n
            any_factors = false
            prime_index = 0
            until any_factors || prime_index == len
                any_factors = factor? candidate, @@primes[prime_index]
                prime_index += 1
            end
            @@primes.push candidate unless any_factors
            candidate += 2
        end
    end
end

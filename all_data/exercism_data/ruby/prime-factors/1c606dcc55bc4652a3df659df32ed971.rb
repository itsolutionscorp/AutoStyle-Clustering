class Array
    def reduce_sorted_up_to(max, memo)
        index = 0
        x = self[index]
        while x && x <= max
            memo = yield memo, x
            index += 1
            x = self[index]
        end
        memo
    end
end

module PrimeFactors
    @@primes = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,91,97]

    def self.for(n)
        get_primes_up_to(root(n))
        remainder = n
        prime_factors = @@primes.reduce_sorted_up_to(root(n), []) do |factors, prime|
            while factor? remainder, prime
                remainder = remainder / prime
                factors.push prime
            end
            factors
        end
        prime_factors.push remainder if remainder > 1
        prime_factors
    end

    private

    def self.root(n)
        Math.sqrt(n).to_i
    end

    def self.get_primes_up_to(n)
        (@@primes.last + 2).step(n, 2) do |candidate|
            @@primes.push(candidate) unless any_factors? candidate
        end
    end

    def self.any_factors?(n)
        @@primes.reduce_sorted_up_to(root(n), false) do |factors_exist, prime|
                return true if factors_exist
                factor?(n, prime)
        end
    end

    def self.factor?(n, prime)
        n >= prime && n % prime == 0
    end

end
    

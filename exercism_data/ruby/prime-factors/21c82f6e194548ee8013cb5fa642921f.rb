module PrimeFactors
    def self.for(n)
        remainder = n
        (2..n).reduce([]) do |factors, possible_factor|
            while possible_factor <= n && factor?(remainder, possible_factor)
                remainder = remainder / possible_factor
                factors.push possible_factor
            end
            factors
        end
    end

    private
    def self.factor?(n, prime)
        n >= prime && n % prime == 0
    end

end

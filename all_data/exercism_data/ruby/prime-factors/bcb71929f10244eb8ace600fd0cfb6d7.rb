module PrimeFactors
    def self.for(n)
        remainder = n
        n.times.reduce([]) do |factors, i|
            possible_factor = i + 2
            while possible_factor <= n && factor?(remainder, possible_factor)
                remainder = remainder / possible_factor
                factors.push possible_factor
            end
            factors
        end
    end

    private

    def self.root(n)
        Math.sqrt(n).floor
    end

    def self.factor?(n, prime)
        n >= prime && n % prime == 0
    end

end

module Prime
    PRIMES = [2] + (3..1_000_000).step(2).select do |i|
        2.upto(Math.sqrt i).none? { |j| (i % j).zero? }
    end

    def Prime.nth n
        raise ArgumentError if n <= 0
        PRIMES[n - 1]
    end
end

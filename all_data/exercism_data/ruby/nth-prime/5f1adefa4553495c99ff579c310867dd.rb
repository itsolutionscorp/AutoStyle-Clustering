class Prime
    def self.nth(number)
        raise(ArgumentError) if number == 0

        counter = 0
        prime = nil
        (1..1000000).each do |n|
            counter += 1 if is_prime? n
            prime = n
            break if counter == number
        end

        prime
    end

    private

    def self.is_prime?(number)
        return true if number == 2
        return false if number <= 1 || number % 2 == 0
        
        divisors = (3..Math.sqrt(number)).step(2)
        !divisors.any? { |d| number % d == 0 }
    end
end

class PrimeFactors
  def self.for(number)
    prime_factors = []

    while number != 1
      (2..number).each do |n|
        if number % n == 0
          prime_factors << n
          number /= n
          break
        end
      end
    end

    prime_factors
  end
end

class PrimeFactors
  def self.for(number)
    (2..number).inject([]) do |prime_factors, i|
      return prime_factors if number == 1
      while number % i == 0
        number /= i
        prime_factors << i
      end
      prime_factors
    end
  end
end

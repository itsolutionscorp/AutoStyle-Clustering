class PrimeFactors
  
  def self.for(number)
    current_number = number
    (2..number).each_with_object([]) do |possible_factor, prime_factors|
      while current_number % possible_factor == 0
        prime_factors << possible_factor
        current_number /= possible_factor
      end
      prime_factors
    end
  end

end

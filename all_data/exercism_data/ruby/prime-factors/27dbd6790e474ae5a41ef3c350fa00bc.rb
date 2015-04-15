class PrimeFactors
  
  def self.for(number)
    current_number = number
    (2..number).each_with_object([]) do |possible_factor, prime_factors|
      while current_number.modulo(possible_factor).zero?
        prime_factors << possible_factor
        current_number /= possible_factor
      end
      return prime_factors if current_number == 1
    end
  end

end

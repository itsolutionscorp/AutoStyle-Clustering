class PrimeFactors
  
  def self.for(number)
    if number > 1
      prime_factors = []
      current_number = number
      (2..number).each do |possible_factor|
        loop do
          break unless current_number % possible_factor == 0

          prime_factors << possible_factor
          current_number /= possible_factor
        end
      end
      prime_factors
    else
      []
    end
  end

end

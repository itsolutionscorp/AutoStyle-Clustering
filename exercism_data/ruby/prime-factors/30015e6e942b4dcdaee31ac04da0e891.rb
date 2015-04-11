module PrimeFactors
  def self.for n
    factors = []
    current_value = n

    current_factor = 2

    while current_value > 1
      if current_value % current_factor == 0
        current_value /= current_factor
        factors << current_factor
      else
        current_factor += 1        
      end
    end

    factors
  end
end

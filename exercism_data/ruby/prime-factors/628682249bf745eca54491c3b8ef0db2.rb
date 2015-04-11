class PrimeFactors
  def self.for(number)
    my_factors = []
    if number % 2 == 0
      my_factors << 2
    elsif number % 3 == 0
      my_factors << 3
    else
      my_factors
    end
  end


end

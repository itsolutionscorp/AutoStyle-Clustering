class PrimeFactors
  def self.for(number)
    [].tap do |factors|
      2.upto(number) do |possible_factor|
        break if possible_factor > number
        while number.modulo(possible_factor).zero?
          number /= possible_factor
          factors << possible_factor
        end
      end
    end
  end
end

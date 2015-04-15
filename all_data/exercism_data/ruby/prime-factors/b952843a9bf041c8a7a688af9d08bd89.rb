class PrimeFactors

  def self.for(number)
    return [] if number == 1
    
    results = []
    (2..number).each do |test|
      while number % test == 0
        number = number / test
        results << test
        return results if number == 1
      end
    end
    return [number] if results.empty?
    results
  end
  
end

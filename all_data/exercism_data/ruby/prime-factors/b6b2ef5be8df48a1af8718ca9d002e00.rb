# Returns an array with the prime factors of a positive integer.
class PrimeFactors
  class << self
    def for(number)
      @number = number.to_i
      raise ArgumentError if @number <= 0
      factor(number)
    end
    
    private

      def factor(number)
        divisor = 2
        results = []
        while number > 1
          while number % divisor == 0
            results << divisor
            number /= divisor
          end
          divisor+=1
        end
        results
      end
  end
end

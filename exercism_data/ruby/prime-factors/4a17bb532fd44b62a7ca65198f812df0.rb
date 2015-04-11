class PrimeFactors
  class << self
    def for(number, result = [])
      return result if number == 1

      lowest_factor = if number.even?
        2
      else
        (3..number/3).step(2).detect do |n|
          number % n == 0
        end || number
      end

      PrimeFactors.for(number / lowest_factor, result.push(lowest_factor))
    end
  end
end

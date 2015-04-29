require 'prime'

class PrimeFactors
  def self.for num
    return [] if num == 1
    return [num] if Prime.prime? num

    min_factor = (2...num).detect { |i| Prime.prime?(i) and num % i == 0 }
    [min_factor, self.for(num / min_factor)].flatten
  end
end

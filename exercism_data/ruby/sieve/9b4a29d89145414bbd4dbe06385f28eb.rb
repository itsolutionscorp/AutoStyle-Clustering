class Fixnum
  def is_divisible_by?(divisor)
    self%divisor == 0
  end
end
class Sieve
  attr_reader :max_limit
  def initialize(max_limit)
    @max_limit = max_limit
  end

  def primes
    all_numbers = (2..max_limit).to_a
    divisor = 2
    while divisor <= max_limit
      all_numbers.delete_if { |number_to_check| (number_to_check > divisor) && number_to_check.is_divisible_by?(divisor) }
      divisor += 1
    end
    all_numbers
  end
end
      

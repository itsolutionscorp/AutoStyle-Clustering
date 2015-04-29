require 'prime'

class Raindrops
  def self.convert(number)
    factors = self.list_of_unique_prime_factors(number)
    cumulator = self.multiple_of_three(factors) + self.multiple_of_five(factors) + self.multiple_of_seven(factors)
    cumulator = number.to_s if cumulator.empty?
    cumulator
  end

  def self.list_of_unique_prime_factors(number)
    number.prime_division.map{|arr| arr[0]}
  end

  def self.has_number_as_factor?(number, factors)
    factors.include?(number)
  end

  def self.multiple_of_three(factors)
    cumulator = ""
    cumulator <<  "Pling" if self.has_number_as_factor?(3, factors)
    cumulator
  end

  def self.multiple_of_five(factors)
    cumulator = ""
    cumulator <<  "Plang" if self.has_number_as_factor?(5, factors)
    cumulator
  end

  def self.multiple_of_seven(factors)
    cumulator = ""
    cumulator <<  "Plong" if self.has_number_as_factor?(7, factors)
    cumulator
  end
end

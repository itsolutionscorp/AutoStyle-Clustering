class Palindromes
  def initialize(min_factor: 1, max_factor: nil,
                 generator_class: BruteForcePalindromicNumberGenerator)
    @generator = generator_class.new(min_factor, max_factor)
  end

  def generate
    @palindromes ||= generator.call
  end

  def smallest
    @palindromes.min_by(&:value)
  end

  def largest
    @palindromes.max_by(&:value)
  end

  private

  attr_reader :generator
end

class BruteForcePalindromicNumberGenerator
  def initialize(min_factor, max_factor)
    @min_factor = min_factor
    @max_factor = max_factor
  end

  def call
    palindromes_with_factors.map do |value, factors|
      PalindromicNumber.new(value, factors)
    end
  end

  private

  def palindromes_with_factors
    factor_combinations.each_with_object(hash_of_arrays) do |factors, palindromes|
      product = factors.reduce(:*)
      palindromes[product] << factors if palindrome?(product)
    end
  end

  def factor_combinations
    (min_factor..max_factor).to_a.repeated_combination(2)
  end

  def hash_of_arrays
    Hash.new { |hash, key| hash[key] = [] }
  end

  def palindrome?(number)
    number.to_s == number.to_s.reverse
  end

  attr_reader :min_factor, :max_factor
end

class PalindromicNumber
  attr_reader :value, :factors

  def initialize(value, factors)
    @value = value
    @factors = factors
  end
end

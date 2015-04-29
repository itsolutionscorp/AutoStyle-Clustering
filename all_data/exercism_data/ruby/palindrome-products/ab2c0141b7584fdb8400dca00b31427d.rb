class Palindromes
  Palindrome = Struct.new(:value, :factors)

  def initialize(max_factor: 99, min_factor: 1)
    @max, @min = max_factor, min_factor
  end

  def generate
    [*@min..@max].repeated_combination(2) do |pair|
      product = pair.reduce(:*)
      palindromes[product] << pair if palindrome? product
    end
  end

  def largest
    Palindrome.new(*palindromes.max)
  end

  def smallest
    Palindrome.new(*palindromes.min)
  end

private

  def palindrome?(number)
    digits = number.to_s
    0.upto(digits.length / 2).all? { |index| digits[index] == digits[-index-1] }
  end

  def palindromes
    @palindromes ||= Hash.new { |hash, key| hash[key] = [] }
  end
end

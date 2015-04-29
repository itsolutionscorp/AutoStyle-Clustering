class Palindromes
  Palindrome = Struct.new(:value, :factors)

  attr_accessor :largest, :smallest

  def initialize(max_factor:, min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
  end

  def generate
    palindromes = Hash.new { |hash, key| hash[key] = [] }

    (min_factor..max_factor).to_a.combination(2) do |factors|
      value = factors.reduce(:*)
      palindromes[value] << factors if palindrome?(value)
    end

    (min_factor..max_factor).each do |factor|
      factors = [factor, factor]
      value = factors.reduce(:*)
      palindromes[value] << factors if palindrome?(value)
    end

    largest_palindrome_value = palindromes.keys.sort.last
    self.largest = Palindrome.new(largest_palindrome_value, palindromes[largest_palindrome_value])

    smallest_palindrome_value = palindromes.keys.sort.first
    self.smallest = Palindrome.new(smallest_palindrome_value, palindromes[smallest_palindrome_value])

    self
  end

  private

  attr_reader :max_factor, :min_factor

  def palindrome?(value)
    value.to_s == value.to_s.reverse
  end
end

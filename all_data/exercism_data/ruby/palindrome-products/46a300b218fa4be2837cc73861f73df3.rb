class Palindromes
  Palindrome = Struct.new(:value, :factors)

  def initialize(max_factor:, min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
    @palindromes = Hash.new { |hash, key| hash[key] = [] }
  end

  def generate
    return self unless palindromes.empty?

    (min_factor..max_factor).each do |factor1|
      (min_factor..max_factor).each do |factor2|
        next if factor1 > factor2
        value = factor1 * factor2
        palindromes[value] << [factor1, factor2] if palindrome?(value)
      end
    end

    self
  end

  def largest
    largest_value = palindromes.keys.sort.last
    Palindrome.new(largest_value, palindromes[largest_value])
  end

  def smallest
    smallest_value = palindromes.keys.sort.first
    Palindrome.new(smallest_value, palindromes[smallest_value])
  end

  private

  attr_reader :max_factor, :min_factor, :palindromes

  def palindrome?(value)
    value.to_s == value.to_s.reverse
  end
end

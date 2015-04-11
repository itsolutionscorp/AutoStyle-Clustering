require 'set'

class Palindromes
  attr_reader :smallest, :largest

  def initialize(min_factor: 0, max_factor:)
    @min_factor = min_factor
    @max_factor = max_factor
    @palindromes = {}
    @smallest = nil
    @largest = nil
  end

  def generate
    range = @min_factor..@max_factor
    range.each do |factor1|
      range.each do |factor2|
        product = factor1 * factor2
        if Palindrome.palindrome?(product)
          add_palindrome(factor1, factor2, product)
        end
      end
    end
  end

  private

  def add_palindrome(factor1, factor2, product)
    existing_palindrome = @palindromes[product]
    if existing_palindrome
      existing_palindrome.add_factors(factor1, factor2)
    else
      palindrome = Palindrome.new(factor1, factor2)
      @palindromes[product] = palindrome
      update_extreme_values(palindrome)
    end
  end

  def update_extreme_values(palindrome)
    if smallest.nil?
      @smallest = palindrome
      @largest = palindrome
    elsif palindrome.value < smallest.value
      @smallest = palindrome
    elsif palindrome.value > largest.value
      @largest = palindrome
    end
  end
end

class Palindrome
  attr_reader :value

  def initialize(factor1, factor2)
    @value = factor1 * factor2
    @factor_set = Set.new
    add_factors(factor1, factor2)
  end

  def factors
    @factor_set.to_a
  end

  def add_factors(factor1, factor2)
    @factor_set << [factor1, factor2].sort
  end

  def self.palindrome?(n)
    n.to_s == n.to_s.chars.reverse.join
  end
end

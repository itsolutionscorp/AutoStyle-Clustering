class Palindromes
  def initialize(min_factor: 1, max_factor: 100)
    @min_factor, @max_factor = min_factor, max_factor
  end

  def generate
    factors = (min_factor..max_factor).to_a
    product_pairs = factors.product(factors).
      uniq { |a| a.sort }.
      group_by { |x, y| x * y }

    @palindromes = product_pairs.each_with_object([]) do |(product, pairs), a|
      a << Palindrome.new(product, pairs) if product.to_s.reverse == product.to_s
    end
  end

  def smallest
    palindromes.min
  end

  def largest
    palindromes.max
  end

  private

  attr_reader :min_factor, :max_factor, :palindromes
end


class Palindrome
  include Comparable

  attr_reader :value, :factors

  def initialize(value, factors)
    @value, @factors = value, factors
  end

  def <=>(other)
    value <=> other.value
  end
end

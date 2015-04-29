class Palindromes
  def initialize(min_factor: 1, max_factor: 100)
    @min_factor, @max_factor = min_factor, max_factor
  end

  def generate
    @palindromes = pairs_by_product.lazy.
      map { |product, pairs| Product.new(product, pairs) }.
      select(&:palindrome?)
  end

  def smallest
    palindromes.min
  end

  def largest
    palindromes.max
  end

  private

  attr_reader :min_factor, :max_factor, :palindromes

  def pairs_by_product
    factors.product(factors).
      uniq { |pair| pair.sort }.
      group_by { |f1, f2| f1 * f2 }
  end

  def factors
    (min_factor..max_factor).to_a
  end
end


class Product
  include Comparable

  attr_reader :value, :factors

  def initialize(value, factors)
    @value, @factors = value, factors
  end

  def palindrome?
    value.to_s.reverse == value.to_s
  end

  def <=>(other)
    value <=> other.value
  end
end

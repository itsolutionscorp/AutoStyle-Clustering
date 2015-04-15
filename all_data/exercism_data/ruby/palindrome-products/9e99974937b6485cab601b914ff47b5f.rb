require 'ostruct'

class Palindromes
  def initialize(options={})
    min_factor = options.fetch(:min_factor, 1)
    max_factor = options.fetch(:max_factor)
    @factors = [*min_factor..max_factor]
    @palindromes = []
  end

  def generate
    @factors.product(@factors).each do |factor_a, factor_b|
      product = factor_a * factor_b
      @palindromes << product if product.to_s == product.to_s.reverse
    end
  end

  def largest
    palindrome = @palindromes.sort.last
    OpenStruct.new(value: palindrome, factors: factor(palindrome))
  end

  def smallest
    palindrome = @palindromes.sort.first
    OpenStruct.new(value: palindrome, factors: factor(palindrome))
  end

private

  def factor(value)
    @factors.map do |factor|
      quotient, remainder = value.divmod(factor)
      [factor, quotient] if remainder == 0 && @factors.include?(quotient)
    end.compact.map(&:sort).uniq
  end
end

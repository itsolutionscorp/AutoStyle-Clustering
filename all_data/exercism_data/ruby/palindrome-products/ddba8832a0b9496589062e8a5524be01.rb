class Palindrome
  attr_reader :value

  def initialize(value)
    @value = value
  end

  def factors
    @factors ||= set_factors
  end

  private

  def set_factors
    factor_length = (value.to_s.length + 1) / 2

    (10**(factor_length - 1)..Math.sqrt(value).to_i).map do |p|
      q = value / p
      [p, q] if value % p == 0 && q.to_s.length == factor_length
    end.compact
  end
end

class Palindromes
  def initialize(options = {})
    @max_factor = options[:max_factor]
    @min_factor = options[:min_factor] || 10**(@max_factor.to_s.length - 1)
    @palindromes = []
  end

  def generate
    (@min_factor..@max_factor).each do |p|
      (p..@max_factor).each do |q|
        candidate = p * q
        @palindromes << Palindrome.new(candidate) if is_palindrome?(candidate)
      end
    end
  end

  def largest
    @palindromes.max_by(&:value)
  end

  def smallest
    @palindromes.min_by(&:value)
  end

  private

  def is_palindrome?(num)
    num.to_s == num.to_s.reverse
  end
end

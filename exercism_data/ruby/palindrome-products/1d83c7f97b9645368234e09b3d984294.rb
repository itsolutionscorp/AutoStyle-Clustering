class Fixnum
  def divisible_by?(other)
    self % other == 0
  end
end

class Palindrome

  def self.is_palindrome?(candidate)
    candidate.to_s.reverse == candidate.to_s
  end

  attr_reader :value

  def initialize(value, multipliers = nil)
    @value = value
    @multipliers = multipliers
  end

  def factors
    factors = @multipliers ? limit(calculate) : calculate
    sort_pairs(factors)
  end

  private

  def calculate
    (1..value).select do |i|
      value.divisible_by?(i)
    end
    .map { |i| [i, (value / i)] }
  end

  def limit(factors)
    factors.select do |pair|
      pair.all? { |r| @multipliers.include?(r) }
    end
  end

  def sort_pairs(factors)
    factors.map do |pair|
      pair.sort
    end
    .uniq
  end

end

class Palindromes
  def initialize(min_factor: 1, max_factor: nil)
    @min = min_factor
    @max = max_factor
  end

  def generate
    multipliers.each do |i|
      multipliers.each do |j|
        product = i * j
        add_new(product) if Palindrome.is_palindrome?(product)
      end
    end
    palindromes
  end

  def largest
    @palindromes.max_by { |p| p.value }
  end

  def smallest
    @palindromes.min_by { |p| p.value }
  end

  private

  def palindromes
    @palindromes ||= []
  end

  def multipliers
    @multipliers ||= (@min..@max).to_a
  end

  def add_new(product)
    palindromes << Palindrome.new(product, multipliers)
  end

end

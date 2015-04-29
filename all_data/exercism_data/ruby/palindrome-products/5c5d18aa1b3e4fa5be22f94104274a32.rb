class Palindromes
  attr_reader :min_factor, :max_factor

  def initialize(opts)
    @min_factor = opts[:min_factor] || 1
    @max_factor = opts[:max_factor]
  end

  def generate
    palindromes = []
    multipliers.each do |i|
      multipliers.each do |j|
        product = i * j
        if palindrome?(product)
          palindromes << Palindrome.new(product, min_factor, max_factor)
        end
      end
    end
    palindromes
  end

  def largest
    generate.max_by{ |p| p.value }
  end

  def smallest
    generate.min_by{ |p| p.value }
  end

  private

  def palindrome?(number)
    digits = number.to_s.chars
    size = digits.length
    half1, half2 = digits[0..(size/2)], digits[(size/2)..-1]
    half1.pop if half1.length > half2.length
    half1 == half2.reverse
  end

  def multipliers
    (min_factor..max_factor).to_a
  end

end

class Palindrome
  attr_reader :value, :min, :max

  def initialize(value, min, max)
    @value = value
    @min = min
    @max = max
  end

  def factors
    factors = find_factors
    sorted = sort_factors(factors)
    scope_to_range(sorted)
  end

  def find_factors
    results = []
    result = (1..value).to_a.each do |i|
       results << [i, (value / i)] if value % i == 0
    end
    results
  end

  def sort_factors(factors)
    factors.map do |r|
      r.sort
    end.uniq
  end

  def scope_to_range(factors)
    factors.select do |v|
      v.all? {|w| (min..max).include?(w)}
    end
  end

end

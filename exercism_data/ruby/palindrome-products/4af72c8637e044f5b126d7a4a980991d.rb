class Palindromes
  def initialize(opts)
    @range = ((opts[:min_factor] || 1)..(opts[:max_factor] || 10)).to_a
  end

  def generate
    @palindromes = @range.repeated_combination(2).to_a
      .each_with_object(Hash.new([])) { |p, h| h[p.reduce(:*)] += [p] }
      .select { |n, _| is_palindrome?(n) }
  end

  def largest
    get(:max)
  end

  def smallest
    get(:min)
  end

  def get(type)
    palindrome = @palindromes.keys.send(type)
    Palindrome.new(palindrome, @palindromes[palindrome].map(&:sort).uniq)
  end

  def is_palindrome?(n)
    n.to_s == n.to_s.reverse
  end
end

class Palindrome
  attr_reader :value, :factors

  def initialize(value, factors)
    @value = value
    @factors = factors
  end
end

class Palindromes
  attr_reader :max_factor, :min_factor, :palindromes

  def initialize(options={})
    @max_factor = options[:max_factor]
    @min_factor = options[:min_factor] || 1
    @palindromes = Hash.new { |hash, key| hash[key] = Palindrome.new(key) }
  end

  def generate
    (min_factor..max_factor).each do |a|
      (min_factor..a).each do |b|     
        value = a * b        
        palindromes[value].factors << [a, b].sort if palindrome?(value)
      end
    end
  end

  def palindrome?(value)
    p = value.to_s
    p == p.reverse
  end

  def sorted_palindromes
    palindromes.values.sort_by(&:value)
  end

  def smallest
    sorted_palindromes.first
  end

  def largest
    sorted_palindromes.last
  end

end

class Palindrome
  attr_reader :value, :factors

  def initialize(value)
    @value = value
    @factors = []
  end

end

class Palindromes
  class Palindrome
    attr_reader :value, :factors
    def initialize value
      @value = value
      @factors = []
    end
    
    def insert_factors a,b
      @factors << [a,b].sort if a*b == value
    end

    def <=> other
      self.value <=> other.value
    end
  end
  
  attr_reader :max,:min
  def initialize max_factor: 1, min_factor: 1
    @max = max_factor
    @min = min_factor
  end
  
  def generate
    @palindromes = palindrome_combinations.each_with_object({}) do |(a,b),list|
      product = a*b
      list[product] = Palindrome.new(product) if list[product].nil?
      list[product].insert_factors a,b  
    end.values.sort
  end
  
  def largest
    @palindromes.last
  end
  
  def smallest
    @palindromes.first
  end
  
  private
  def is_palindrome? value
    value.to_s == value.to_s.reverse
  end
  def palindrome_combinations
    combos.select{|(a,b)| is_palindrome? a*b }.map(&:sort).uniq
  end
  def combos
    min.upto(max).to_a.repeated_combination(2)
  end
end

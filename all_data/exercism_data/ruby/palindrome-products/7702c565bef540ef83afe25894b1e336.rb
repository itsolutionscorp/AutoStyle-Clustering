class Palindromes

  attr_reader :value, :factors

  def initialize(max_factor: nil, min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
  end

  def palindrome?(num)
    num.to_s == num.to_s.reverse
  end

  def generate
    palindromes = []
    multipliers = @min_factor..@max_factor
    multipliers.each do |x|
      multipliers.each do |y|
        product = x * y
        if palindrome?(product)
          palindromes << product
        end
      end 
    end
    palindromes.sort
  end

  def find_factors
    factors = []
    (@min_factor..@max_factor).each do |num|
      if @value % num == 0
        other_factor = value / num
        unless other_factor > @max_factor || num > @max_factor
          factors << [num, other_factor]
        end
      end
    end
    factors.map {|pair| pair.sort }.uniq
  end

  def largest
    @value = generate.last
    factors = find_factors
    Palindrome_info.new(value, factors)
  end

  def smallest
    @value = generate.first
    factors = find_factors
    Palindrome_info.new(value, factors)
  end

  class Palindrome_info < Palindromes

    attr_reader :value, :factors

    def initialize(value, factors)
      @value = value
      @factors = factors 
    end
  end

end

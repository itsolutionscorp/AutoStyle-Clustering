class Palindromes
  def initialize(options = {})
    @max_factor = options[:max_factor]
    @min_factor = options[:min_factor] || 1
    @palindromes = []
  end

  def generate
    (@min_factor..@max_factor).each do |first|
      (@min_factor..first).each do |second|
        test = first * second
        @palindromes << test if is_palindrome?(test)
      end
    end
  end

  def is_palindrome? number
    number.to_s == number.to_s.reverse
  end

  def largest
    Palindrome.new(@palindromes.max)
  end

  def smallest
    Palindrome.new(@palindromes.min)
  end
end

class Palindrome
  def initialize(palindrome)
    @palindrome = palindrome
    @factors = []
  end

  def factors
    (1..Math::sqrt(@palindrome)).each do |divisor|
      if @palindrome % divisor == 0
        div_one = divisor
        div_two = @palindrome / divisor
        @factors = [[div_one,div_two]]
      end
    end
    @factors
  end

  def value
    @palindrome
  end
end

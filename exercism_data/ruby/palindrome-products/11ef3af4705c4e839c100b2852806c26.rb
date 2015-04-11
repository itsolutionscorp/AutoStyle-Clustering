class Palindromes
  # all palindromic numbers with even digits are divisible by 11
  def initialize max_factor:, min_factor: 1
    @max_factor, @min_factor = max_factor, min_factor
    @palindromes = {}
  end
  
  def generate
    (@min_factor..@max_factor).each do |x|
      (@min_factor..x).each do |y| 
        value = x*y
        if value.to_s.reverse == value.to_s
          @palindromes[value] ||= (Product.new value)
          @palindromes[value].factors << [y, x]
        end
      end
    end
  end

  def largest
    @palindromes.values.max_by &:value
  end

  def smallest
    @palindromes.values.min_by &:value
  end
end

class Product
  attr_accessor :factors
  attr_reader :value

  def initialize value
    @value = value
    @factors = []
  end
end

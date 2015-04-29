class Palindromes
  attr_reader :max_factor, :min_factor, :factors

  def initialize(args = {})
    @max_factor = args.fetch(:max_factor)
    @min_factor = args.fetch(:min_factor, 1)
    @factors = []
  end

  def generate
    (min_factor..max_factor).each do |i|
      (i..max_factor).each do |k|        
          @factors << [i,k] if palindrome?(i*k)
      end
    end
  end

  def largest
    max = @factors.max_by { |f| f[0] * f[1] }
    max = @factors.select { |f| (f[0] * f[1]) == (max[0] * max[1]) } 
    PalindromeProducts.new(max)
  end

  def smallest
    min = @factors.min_by { |f| f[0] * f[1] }
    min = @factors.select { |f| (f[0] * f[1]) == (min[0] * min[1]) } 
    PalindromeProducts.new(min)
  end

  private

  def palindrome?(num)
    s = num.to_s
    s == s.reverse
  end
end

PalindromeProducts = Struct.new(:factors) do
  def value
    factors[0][0] * factors[0][1]
  end
end

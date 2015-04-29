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
          @factors << Factors.new(i,k) if palindrome?(i*k)
      end
    end
  end

  def largest
    get_factors(:max)
  end

  def smallest
    get_factors(:min)
  end

  private

  def palindrome?(num)
    s = num.to_s
    s == s.reverse
  end

  def get_factors(method)
    minmax  = @factors.send(method) { |a,b| a.value <=> b.value }
    factors = @factors.select { |f| f.value == minmax.value }
                      .map { |f| [f.a, f.b] }  # convert to array of factors for testing
    MultipleFactors.new(minmax.value, factors)
  end
end


Factors = Struct.new(:a, :b) do
  def value
    a * b
  end
end

MultipleFactors = Struct.new(:value, :factors)
 

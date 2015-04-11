class Palindrome
  attr_reader :value, :factors

  def initialize num, f1, f2
    @value = num
    @factors = [[f1, f2].sort]
  end

  def << (tuple)
    @factors << tuple.sort
    @factors = @factors.uniq
  end
end

class Palindromes
  attr_reader :largest, :smallest

  def initialize args={}
    @max_factor = args.fetch( :max_factor, 1)
    @min_factor = args.fetch( :min_factor, 1)
    @collection = {}
  end

  def generate
    @max_factor.downto(@min_factor) do |a|
      @max_factor.downto(@min_factor).each do |b|
        prod = a*b
        store_palindrome(prod, a, b) if is_palindrome? prod
      end
    end
  end

private
  def store_palindrome product, f1, f2
    if @collection[product].nil?
      @collection[product] = Palindrome.new product, f1, f2
      @largest = @collection[@collection.keys.max]
      @smallest = @collection[@collection.keys.min]
    else
      @collection[product] << [f1, f2]
    end
  end

  def is_palindrome? n
    str = n.to_s
    str == str.reverse
  end

end

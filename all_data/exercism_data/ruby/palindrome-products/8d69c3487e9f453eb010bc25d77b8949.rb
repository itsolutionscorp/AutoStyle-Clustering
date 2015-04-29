class Palindromes
  def initialize(max_factor: 0, min_factor: 1)
    @min_factor, @max_factor = min_factor, max_factor
    @palindroms = Hash.new { |h, k| h[k] = Product.new(k) }
  end

  def generate
    min_factor.upto(max_factor) do |i|
      i.upto(max_factor) do |j|
        prod = i * j
        @palindroms[prod] << [i, j] if prod.palindrom?
      end
    end
  end

  def largest
    @palindroms[products.max]
  end

  def smallest
    @palindroms[products.min]
  end

  private
  attr_reader :min_factor, :max_factor

  def products
    @palindroms.keys
  end
end

class Product
  attr_reader :value
  def initialize(value)
    @value = value
    @factors = {}
  end

  def << factors
    @factors[factors] = true
  end

  def factors
    @factors.keys
  end
end

class Integer
  def palindrom?
    to_s.palindrom?
  end
end

class String
  def palindrom?
    self == self.reverse
  end
end

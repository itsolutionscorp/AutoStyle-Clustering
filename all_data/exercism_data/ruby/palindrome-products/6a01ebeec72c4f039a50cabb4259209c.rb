class Palindromes
  def initialize(max_factor: 0, min_factor: 1)
    @min_factor, @max_factor = min_factor, max_factor
    @palindroms = {}
  end

  def generate
    min_factor.upto(max_factor) do |i|
      i.upto(max_factor) do |j|
        if (prod = i * j).palindrom?
          @palindroms[prod] ||= Palindrom.new(prod)
          @palindroms[prod] << [i, j]
        end
      end
    end
  end

  def largest
    key = @palindroms.keys.max
    @palindroms[key]
  end

  def smallest
    key = @palindroms.keys.min
    @palindroms[key]
  end

  private
  attr_reader :min_factor, :max_factor
end

class Palindrom
  attr_reader :value, :factors
  def initialize(value)
    @value = value
    @factors = []
  end

  def << factors
    @factors << factors
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

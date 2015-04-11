class Integer
  def palindrome?
    to_s.reverse == to_s
  end
end
class Palindromes
  def initialize (dict)
    @min, @max = dict[:min_factor] || 1, dict[:max_factor]
  end
  def generate
    @pals = (@min..@max).to_a.product((@min..@max).to_a).reduce({}){|d, v|
      i, j = v
      if i <= j and (i * j).palindrome?
        d[i * j] = [] unless d[i * j]
        d[i * j] << [i, j] 
      end
      d
    }
  end
  def largest
    Palindrome.new(@pals.keys.max, @pals[@pals.keys.max])
  end
  def smallest
    Palindrome.new(@pals.keys.min, @pals[@pals.keys.min])
  end
end
class Palindrome
  attr_reader :value, :factors
  def initialize (v, fs)
    @value = v
    @factors = fs
  end
end

class Palindrome < Struct.new(:value, :factors)
  def << (fact)
    factors << fact
  end
end

class Palindromes
  attr_reader :max_factor, :min_factor, :palindromes

  def initialize(options = {})
    @max_factor  = options.fetch(:max_factor)
    @min_factor  = options.fetch(:min_factor, 1)
    @palindromes = Hash.new {|h,k| h[k] = Palindrome.new(k, []) }
  end

  def generate
    factors do |x,y|
      palindromes[x * y] << [x, y] if is_palindrome?(x * y)
    end
  end

  def largest
    palindromes.max_by {|k, _| k }[1]
  end
  
  def smallest
    palindromes.min_by {|k, _| k }[1]
  end

  private

  def factors
    (min_factor..max_factor).each do |x|
      (x..max_factor).each do |y|
        yield x, y
      end
    end
  end

  def is_palindrome?(n)
    n.to_s.reverse == n.to_s
  end
end

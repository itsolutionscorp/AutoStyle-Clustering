class Palindromes

  def initialize(args = {})
    @max = args[:max_factor] || 9
    @min = args[:min_factor] || 1
    @rge = [*@min..@max]
    @mtrx = []
  end

  def generate
    @rge.each do |n1|
      [*n1..@max].each do |n2|
        temp = (n1*n2).to_s
        @mtrx << [n1,n2] if temp.eql? temp.reverse
      end
    end
  end

  def largest
    temp = @mtrx.max_by{|e| e[1] and e[0]+e[1]}
    Palindrome.new((temp[0]*temp[1]), temp)
  end

  def smallest
    temp = @mtrx.min_by{|e| e[1] and e[0]+e[1]}
    Palindrome.new((temp[0]*temp[1]), temp)
  end
end

class Palindrome
  attr_reader :value, :factors

  def initialize(value, factors)
    @value = value
    @factors = [factors]
  end
end

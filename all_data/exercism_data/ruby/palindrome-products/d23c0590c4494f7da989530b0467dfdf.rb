class Palindromes

  def initialize args
    @max = args[:max_factor]
    @min = args[:min_factor] || 1
  end

  attr_reader :largest, :smallest

  def generate
    pally_nums = (@min..@max).each_with_object([]) {|i, arr|
      (@min..@max).each{|j|
        j = i * j
      arr.push(j) if j.to_s == j.to_s.reverse
      }}.sort
    @largest = Palindrome.new(pally_nums[-1], @min, @max)
    @smallest = Palindrome.new(pally_nums[0], @min, @max)
  end

end

class Palindrome

  attr_reader :value

  def initialize number, min, max
    @value = number
    @min = min
    @max = max
  end

  def factors
    (@min..@value ** 0.5).each_with_object([]) {|i, arr|
      arr.push([i, @value / i]) if @value % i == 0 && value / i <= @max
    }
  end


end

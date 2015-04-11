class Palindromes

  class Palindrome
    attr_reader :value, :factors
    def initialize(value, factors = [])
      @value, @factors = value, factors
    end
  end

  def initialize(max_factor: nil, min_factor: 1)
    @max_factor, @min_factor = max_factor, min_factor
  end
  
  def generate
    palindromes = Hash.new { |h,key| h[key] = Palindrome.new(key) }
    enumerate_palindromes do |product, factors|
      palindromes[product].factors << factors
    end
    @ordered_palindromes = palindromes.values.sort_by(&:value)
  end

  def largest
    @ordered_palindromes.last
  end

  def smallest
    @ordered_palindromes.first
  end

  private
  def palindrome?(num)
    num == num.to_s.reverse.to_i
  end
  
  def enumerate_palindromes(&block)
    enumerate_factors do |a, b|
      product = a * b
      block.(product, [a, b]) if palindrome?(product)
    end
  end

  def enumerate_factors(&block)
    (@min_factor..@max_factor).each do |a|
      (@min_factor..a).each do |b|
        a < b ? block.(a, b) : block.(b, a)
      end
    end
  end
end

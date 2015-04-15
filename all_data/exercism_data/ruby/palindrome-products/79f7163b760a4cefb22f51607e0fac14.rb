class Palindromes

  class Palindrome
    attr_reader :value, :factors

    def initialize(value, factors = [])
      @value = value
      @factors = factors
    end
  end


  def initialize(max_factor: 1, min_factor: 1)
    @min = min_factor
    @max = max_factor
    @palindromes = []
  end

  def generate
    hash = Hash.new { |h, k| h[k] = Palindrome.new(k) }
    (@min .. @max).each do |x|
      (@min .. x).each do |y|
        product = x * y
        if palindrome?(product)
          hash[product].factors << [x, y].sort
        end
      end
    end

    @palindromes = hash.values.sort_by(&:value)
  end

  def largest
    @palindromes.last
  end

  def smallest
    @palindromes.first
  end

  private
  def palindrome?(product)
    x = product.to_s
    x == x.reverse
  end
end

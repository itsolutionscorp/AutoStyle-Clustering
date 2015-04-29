class Palindromes
  attr_reader :max_factor, :min_factor, :palindromes

  def initialize(opts = {})
    @palindromes = []
    @max_factor = opts[:max_factor]
    @min_factor = opts[:min_factor] || 0
  end

  def generate
    (@min_factor..@max_factor).each do |n1|
      (@min_factor..@max_factor).each do |n2|
        product = n1 * n2

        next unless product.to_s == product.to_s.reverse

        existing_set = @palindromes.find { |s| s.value == product }
        current_set = existing_set ? existing_set : Palindromes::Set.new

        current_set.value = product
        current_set.factors <<= [n1, n2]

        @palindromes << current_set unless @palindromes.include? current_set
      end
    end

    @palindromes = @palindromes.sort { |x, y| x.value <=> y.value }
  end

  def largest
    @palindromes.last
  end

  def smallest
    @palindromes.first
  end

  class Set
    attr_writer :factors
    attr_accessor :value

    def initialize
      @factors = []
      @value = 0
    end

    def factors
      @factors.map(&:sort).uniq
    end
  end
end

class Palindromes
  module StringPatch
    refine String do
      def palindrome?
        reverse == self
      end
    end
  end

  class Palindrome
    attr_reader :factors, :value

    def initialize(value)
      @value = value
      @factors = []
    end
  end

  using StringPatch

  attr_reader :largest, :smallest

  def initialize(max_factor: 1, min_factor: 0)
    @factors = (min_factor..max_factor)
  end

  def generate
    palindromes = Hash.new { |h, k| h[k] = Palindrome.new(k) }
    Array(@factors).repeated_combination(2) do |a, b|
      palindromes[a * b].factors << [a, b] if (a * b).to_s.palindrome?
    end
    @smallest, @largest = palindromes.minmax.map(&:last)
  end
end

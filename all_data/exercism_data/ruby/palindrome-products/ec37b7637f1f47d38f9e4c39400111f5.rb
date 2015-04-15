Palindrome = Struct.new(:value, :factors)

class Palindromes

  attr_reader :range
  def initialize(options)
    max = options.fetch(:max_factor)
    min = options.fetch(:min_factor) { 1 }
    @range = (min..max)
  end

  def generate
    @palindromes = {}
    range.each do |first|
      range.each do |second|
        product = first * second
        if palindrome?(product)
          palindrome = @palindromes[product] || Palindrome.new(product, [])
          palindrome.factors << [first, second].sort
          palindrome.factors.uniq!
          @palindromes[product] = palindrome
        end
      end
    end
  end

  def palindrome?(number)
    number.to_s == number.to_s.reverse
  end

  def largest
    @palindromes.sort_by {|key, _palindrome| key}.last[1]
  end

  def smallest
    @palindromes.sort_by {|key, _palindrome| key}.first[1]
  end

end

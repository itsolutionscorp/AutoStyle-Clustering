class Palindromes
  attr_accessor :max_factor, :min_factor

  def initialize(args)
    @max_factor = args[:max_factor] || 999999
    @min_factor = args[:min_factor] || 0
    @palindromes = Hash.new{ [] }
  end

  def generate
    (min_factor..max_factor).to_a.repeated_combination(2).each do |pair|
      num = pair[0] * pair[1]
      @palindromes[num] += [pair] if palindrome?(num)
    end
  end

  def largest
    Palindrome.new(@palindromes.max_by{|k,v| k})
  end
  
  def smallest
    Palindrome.new(@palindromes.min_by{|k,v| k})
  end

  private

  def palindrome?(num)
    num == num.to_s.reverse.to_i
  end
end

class Palindrome
  attr_reader :factors, :value

  def initialize(args)
    @factors = args[1].map(&:sort).uniq
    @value = args[0]
  end
end

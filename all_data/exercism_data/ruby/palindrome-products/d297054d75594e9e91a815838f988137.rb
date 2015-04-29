require 'ostruct'

class Palindromes
  attr_reader :max_factor, :min_factor
  def initialize(args)
    @max_factor = args[:max_factor] || 9
    @min_factor = args[:min_factor] || 1
  end

  def generate
    @palindromes ||= max_factor.downto(min_factor).inject([]) do |palindromes, n1|
      max_factor.downto(min_factor).each do |n2|
        next unless is_palindromic?(n1*n2)
        value = n1*n2
        factors = [n1,n2].sort
        existing = palindromes.index { |p| p.value == value }
        if existing
          palindromes[existing].factors << factors unless palindromes[existing].factors.include?(factors)
        else
          palindromes << Palindrome.new(value: value, factors: [factors])
        end
      end
      palindromes
    end
  end

  def largest
    @palindromes.sort_by(&:value).last
  end

  def smallest
    @palindromes.sort_by(&:value).first
  end

  private

  def is_palindromic?(n)
    n.to_s == n.to_s.reverse
  end

  class Palindrome
    attr_reader :value, :factors
    def initialize(args)
      @value = args[:value]
      @factors = args[:factors]
    end
  end
end

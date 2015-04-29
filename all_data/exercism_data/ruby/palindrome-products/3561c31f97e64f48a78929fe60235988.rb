require 'ostruct'
class Palindromes
  attr_reader :min, :max, :palindromes

  def initialize options
    @max         = options[:max_factor] || 1
    @min         = options[:min_factor] || 1
    @palindromes = []
    @range       = [*@min..@max]
  end

  def generate
    @palindromes = @range
                  .product(@range)
                  .map    { |a, b| [[a, b].sort, a * b] }
                  .uniq
                  .select { |_, b| b.to_s.chars == b.to_s.chars.reverse }
                  .sort   { |a, b| a.last <=> b.last }
  end

  def largest
    l         = OpenStruct.new
    l.value   = palindromes.last.last
    l.factors = palindromes.select { |p| p.last == l.value }
                           .map    { |a, _| a }
    l
  end

  def smallest
    s         = OpenStruct.new
    s.value   = palindromes.first.last
    s.factors = palindromes.select { |p| p.last == s.value }
                           .map    { |a, _| a }
    s
  end
end

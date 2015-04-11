require 'ostruct'

class Palindromes

  attr_reader :max_factor, :min_factor

  def initialize(max_factor: nil, min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
  end

  def generate
    palindromes = Hash.new { |h, k| h[k] = OpenStruct.new(value: k, factors: []) }

    palindrome_builder do |product, factors|
      palindromes[product].factors << factors
    end

    @ordered_palindromes = palindromes.values.sort_by(&:value)
  end

  def smallest
    @ordered_palindromes.first
  end

  def largest
    @ordered_palindromes.last
  end

  private

  def palindrome?(n)
    as_string = n.to_s
    as_string == as_string.reverse
  end

  def palindrome_builder(&block)
    enumerate_factors do |a, b|
      product = a * b
      factor  = [a, b]

      block.call(product, factor) if palindrome?(product)
    end
  end

  def enumerate_factors(&block)
    (min_factor..max_factor).each do |a|

      (min_factor..a).each do |b|
        a < b ? block.call(a, b) : block.call(b, a)
      end
    end
  end

end

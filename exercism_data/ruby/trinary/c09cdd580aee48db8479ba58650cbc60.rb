class Trinary
  BASE = 3
  VALID_CHARS = "012"

  def initialize(str)
    @str = if str =~ /[^#{VALID_CHARS}]/
             '0'
           else
             str
           end
  end

  attr_reader :str
  
  def to_decimal
    sum = 0
    digits.inject(1) do |factor, digit|
      sum += digit * factor
      factor * BASE
    end
    sum
  end

  private

  def digits
    str.chars.reverse.map(&:to_i)
  end
end
